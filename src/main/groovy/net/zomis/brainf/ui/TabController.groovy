package net.zomis.brainf.ui

import javafx.collections.ListChangeListener
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.scene.control.Tab
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import net.zomis.brainf.analyze.AnalyzeFactory
import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.analyzers.BrainfuckAnalyzers
import net.zomis.brainf.analyze.analyzers.CodeCellRelationAnalysis
import net.zomis.brainf.model.BrainF
import net.zomis.brainf.model.ListCode
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.groovy.GroovyBFContext
import net.zomis.brainf.model.groovy.GroovyListener
import net.zomis.brainf.model.groovy.GroovySupportConverter
import net.zomis.brainf.model.input.StringBuilderOutput
import net.zomis.brainf.model.run.RunStrategy
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.LineNumberFactory

import java.util.concurrent.BlockingQueue
import java.util.concurrent.Future
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ScheduledExecutorService

class TabController implements Initializable {

    @FXML ListView<String> memoryList
    @FXML AnchorPane codePane
    @FXML TextArea output

    private Stage stage;
    private Tab tab;
    private BFTask task;
    LoadSaveHandler loadSave

    CodeArea codeArea;
    @FXML TextField input
    final BlockingQueue<Integer> inputQueue = new LinkedBlockingQueue<>()
    BrainfuckRunner brain
    private Brainalyze analyze
    private EditorStyle styleController
    boolean codeModified
    boolean memoryListUpdate
    GroovySupportConverter converter
    private MainController callback

    void update() {
        codeArea.selectRange(brain.getCode().getCommandIndex(), brain.getCode().getCommandIndex() + 1);

        memoryListUpdate = false
        for (int i = 0; i < brain.getMemory().getMemorySize(); i++) {
            this.memoryList.getItems().set(i, memoryText(i));
        }
        memoryListUpdate = true
    }

    @Override
    void initialize(URL location, ResourceBundle resources) {
        brain = BrainF.createUsingQueueWithMemorySize(inputQueue, 0x1000, new TextAppender(output));
		for (int i = 0; i < brain.getMemory().getMemorySize(); i++) {
			memoryList.getItems().add("");
		}
        memoryList.selectionModel.selectionMode = SelectionMode.MULTIPLE
        memoryList.selectionModel.selectedIndices.addListener(new ListChangeListener<Integer>() {
            @Override
            void onChanged(ListChangeListener.Change<? extends Integer> c) {
                if (analyze == null || !memoryListUpdate) {
                    return
                }
                println "Memory List Selection Change: $c changed to $c.list class ${c.getClass()}"
                styleController.highlightCodeIndexes = analyze?.get(CodeCellRelationAnalysis)?.codeAccessedBy(c.list)
                styleController.applyAll()
            }
        })
        setupCodeArea()
    }

    void setCode(String code) {
        codeArea.replaceText(0, 0, code);
    }

    void setupCodeArea() {
        this.codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        styleController = EditorStyle.setup(codeArea, {analyze}, {
            codeModified = true
            loadSave.modified()
            analyze = null
        }, {
            stage.title = String.format("BrainDuck pos %d col %d", codeArea.getCaretPosition(), codeArea.getCaretColumn())
            callback.positionText.text = String.format("pos %d, col %d", codeArea.getCaretPosition(), codeArea.getCaretColumn())
            memoryListUpdate = false
            memoryList.selectionModel.clearSelection()
            def cellsAccessed = analyze?.get(CodeCellRelationAnalysis)?.codeToCells?.
                    getOrDefault(codeArea.caretPosition, Collections.emptySet())
            cellsAccessed?.forEach({
                memoryList.selectionModel.select(it)
            })
            memoryListUpdate = true
        })
        codeArea.setPrefWidth(600);
        codeArea.setPrefHeight(600);
        AnchorPane.setTopAnchor(codeArea, 0d);
        AnchorPane.setRightAnchor(codeArea, 0d);
        AnchorPane.setLeftAnchor(codeArea, 0d);
        AnchorPane.setBottomAnchor(codeArea, 0d);
        codePane.getChildren().clear()
        codePane.getChildren().add(codeArea);
    }

    private String memoryText(int i) {
        int value = brain.getMemory().getMemory(i);
        char ch = (char) (value < 0 ? 256 + value : value);
        Map<String, Integer> cellNameMap = converter.groovyContext.getCellNames(i)
        String memoryCellNames = cellNameMap ? cellNameMap.toString() : ""
        return Integer.toString(i, 16) + "\t" + value + "\t" +
            String.valueOf(ch).trim() + "\t" +
            (brain.getMemory().getMemoryIndex() == i ? "x" : "") + "\t" +
            memoryCellNames;
    }

    void saveCodeIfRequired() {
        if (codeModified) {
            codeModified = false
            converter = ListCode.newGroovyConverter();
            brain.code.setSource(ListCode.create(converter, codeArea.text));
            brain.setListener(new GroovyListener(converter.groovyContext))
            output.text = ''
            brain.reset();
            inputQueue.clear()
        }
    }

    void setup(Tab tab, Stage stage, MainController callback) {
        this.@tab = tab
        this.@stage = stage
        this.@callback = callback
        this.loadSave = new LoadSaveHandler(this, tab);
    }

    public Tab getTab() {
        return this.@tab;
    }

    void run(RunStrategy strategy) {
        if (brain.code.getNextCommand() == null) {
            brain.reset();
            output.text = ''
            inputQueue.clear()
        }
        if (task && task.running) {
            System.out.println("--- Code already running, cannot start " + strategy);
            // do not allow multiple runs at the same time
            return;
        }
        saveCodeIfRequired();
        BFTask task = new BFTask(tab.getText(), brain, converter, strategy);
        task.setOnSucceeded({e ->
            println "Success"
            update()
        })
        task.setOnFailed({e ->
            println "Failed"
            update()
        })
        callback.startTask(task);
    }

    void stopRun() {
        task?.cancel(true)
    }

    public String getCode() {
        return codeArea.getText();
    }

    @FXML public void performInput(ActionEvent event) {
        input.text.chars().forEach({ inputQueue.add(it) })
        inputQueue.add(10)
        input.text = ""
    }

    void performAnalyze() {
        GroovySupportConverter groovyConverter = ListCode.newGroovyConverter();

        StringBuilder str = new StringBuilder()
        BrainfuckRunner analyzeRunner = BrainF.createUsingQueueWithMemorySize(inputQueue,
            0x1000, new StringBuilderOutput(str));
        analyzeRunner.code.setSource(ListCode.create(groovyConverter, codeArea.text));
        analyzeRunner.setListener(new GroovyListener(groovyConverter.groovyContext))

        final BFTaskAnalyze analyzeTask =
            new BFTaskAnalyze(tab.getText(), analyzeRunner, groovyConverter.groovyContext)
        analyzeTask.setOnSucceeded({e -> analyze = analyzeTask.getValue()})
        callback.startTask(analyzeTask)
    }

}
