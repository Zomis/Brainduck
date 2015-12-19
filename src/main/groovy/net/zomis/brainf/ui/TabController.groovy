package net.zomis.brainf.ui

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ListView
import javafx.scene.control.Tab
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import net.zomis.brainf.model.BrainF
import net.zomis.brainf.model.ListCode
import net.zomis.brainf.model.classic.BrainFCommand
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.run.RunStrategy
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.LineNumberFactory
import org.fxmisc.richtext.StyleSpans
import org.fxmisc.richtext.StyleSpansBuilder

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

class TabController implements Initializable {

    @FXML ListView<String> memoryList
    @FXML AnchorPane codePane
    @FXML TextArea output

    private Stage stage;
    private Tab tab;
    private final AtomicBoolean runSwitch = new AtomicBoolean();
    private final AtomicBoolean codeRunning = new AtomicBoolean();
    LoadSaveHandler loadSave

    CodeArea codeArea;
    @FXML TextField input
    final BlockingQueue<Integer> inputQueue = new LinkedBlockingQueue<>()
    final BrainfuckRunner brain = BrainF.createUsingQueueWithMemorySize(inputQueue, 0x1000);
    boolean codeModified

    void update() {
        codeArea.selectRange(brain.getCode().getCommandIndex(), brain.getCode().getCommandIndex() + 1);
        output.setText(brain.getOutput());

        for (int i = 0; i < brain.getMemory().getMemorySize(); i++) {
            this.memoryList.getItems().set(i, memoryText(i));
        }
    }

    @Override
    void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < brain.getMemory().getMemorySize(); i++) {
			memoryList.getItems().add("");
		}
        setupCodeArea()
    }

    void setCode(String code) {
        codeArea.replaceText(0, 0, code);
    }

    void setupCodeArea() {
        this.codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.richChanges().subscribe({change ->
            if (change.removed.text != change.inserted.text) {
                codeModified = true
                loadSave.modified()
            }
            def highlighting = EditorStyle.computeHighlighting(codeArea)
            codeArea.setStyleSpans(0, highlighting)
        })
        codeArea.setOnKeyReleased({
            stage.setTitle(String.format("BrainDuck pos %d col %d", codeArea.getCaretPosition(), codeArea.getCaretColumn()));
            EditorStyle.applyHighlights(codeArea)
        });
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
        return Integer.toString(i, 16) + "\t" + value + "\t" + String.valueOf(ch).trim() + "\t" + (brain.getMemory().getMemoryIndex() == i ? "x" : "");
    }

    void saveCodeIfRequired() {
        if (codeModified) {
            codeModified = false
            brain.code.setSource(ListCode.create(codeArea.text));
            brain.reset();
            inputQueue.clear()
        }
    }

    void setup(Tab tab, Stage stage) {
        this.@tab = tab
        this.@stage = stage
        this.loadSave = new LoadSaveHandler(this, tab);
    }

    public Tab getTab() {
        return this.@tab;
    }

    void run(ScheduledExecutorService exec, RunStrategy strategy) {
        if (brain.code.getNextCommand() == null) {
            brain.reset();
            inputQueue.clear()
        }
        if (codeRunning.get()) {
            System.out.println("--- Code already running, cannot start " + strategy);
            // do not allow multiple runs at the same time
            return;
        }
        saveCodeIfRequired();
        exec.execute({
            this.codeRunning.set(true);
            this.runSwitch.set(true);
            final AtomicInteger runTimes = new AtomicInteger();
            int count = brain.run(new RunStrategy() {
                @Override
                public boolean start(BrainfuckRunner runner) {
                    return strategy.start(runner);
                }

                @Override
                public boolean next(BrainfuckRunner runner) {
                    if (Thread.interrupted()) {
                        stopCode();
                        return false;
                    }
                    if (!runSwitch.get()) {
                        stopCode();
                        return false;
                    }
                    return strategy.next(runner);
                }

                private void stopCode() {
                    runSwitch.set(true);
                    codeRunning.set(false);
                }
            });
            if (count == 0) {
                System.out.println(strategy + " not started");
            }
            if (Platform.isFxApplicationThread()) {
                update();
            } else {
                Platform.runLater({update()});
            }
            codeRunning.set(false);
        });
    }

    void stopRun() {
        runSwitch.set(false)
    }

    public String getCode() {
        return codeArea.getText();
    }

    @FXML public void performInput(ActionEvent event) {
        input.text.chars().forEach({ inputQueue.add(it) })
        input.text = ""
    }

}
