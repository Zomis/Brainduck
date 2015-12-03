package net.zomis.brainf.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import net.zomis.brainf.analyze.AnalyzeFactory;
import net.zomis.brainf.analyze.Brainalyze;
import net.zomis.brainf.analyze.analyzers.BrainfuckAnalyzers;
import net.zomis.brainf.model.BrainfuckRunner;
import net.zomis.brainf.model.ListCode;
import net.zomis.brainf.model.groovy.GroovyBFContext;
import net.zomis.brainf.model.run.*;

public class MainController implements Initializable {

    @FXML private TabPane tabs;

    private TabController currentTab;
    private final Stage stage;
    private final AtomicBoolean runSwitch = new AtomicBoolean();
    private final AtomicBoolean codeRunning = new AtomicBoolean();

    public MainController(Stage stage) {
        this.stage = stage;
	}
	
	private ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	
    @FXML
    private void runUntilLoopStart(ActionEvent event) {
        runWith(new RunUntilLoopStartStrategy());
    }

    @FXML private void runXTimes(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setContentText("Run count");
        dialog.setHeaderText("How many times do you want to run?");
        dialog.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    dialog.getEditor().setText(oldValue);
                }
            }
        });
        int count = Integer.parseInt(dialog.showAndWait().orElse("0"), 10);
        runWith(new LimitedStepsStrategy(count));
    }

    @FXML
	private void runToCursor(ActionEvent event) {
        currentTab.saveCodeIfRequired();
		int index = currentTab.getCodeArea().getCaretPosition();
		while (brain().getCode().getCommandIndex() < index) {
			brain().step();
		}
		update();
	}

    @FXML
	private void step(ActionEvent event) {
        runWith(new LimitedStepsStrategy());
	}

    @FXML
    private void stopRunning(ActionEvent event) {
        runSwitch.set(false);
    }

    private void runWith(RunStrategy strategy) {
        if (brain().getCode().getNextCommand() == null) {
            brain().reset();
        }
        if (codeRunning.get()) {
            System.out.println("--- Code already running, cannot start " + strategy);
            // do not allow multiple runs at the same time
            return;
        }
        currentTab.saveCodeIfRequired();
        this.exec.execute(() -> {
            this.codeRunning.set(true);
            this.runSwitch.set(true);
            final AtomicInteger runTimes = new AtomicInteger();
            int count = brain().run(new RunStrategy() {
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
                Platform.runLater(() -> update());
            }
            codeRunning.set(false);
        });
    }

    @FXML private void stepOut(ActionEvent event) {
        runWith(new StepOutStrategy());
    }

    @FXML private void runCode(ActionEvent event) {
        runWith(new UntilEndStrategy());
    }

    @FXML private void stepContinue(ActionEvent event) {
        runWith(new StepContinueStrategy());
    }

    @FXML private void codeAreaFix(ActionEvent event) {
        String code = currentTab.getCodeArea().getText();
        int index = currentTab.getCodeArea().getCaretPosition();
        currentTab.setupCodeArea();
        currentTab.getCodeArea().replaceText(code);
        currentTab.getCodeArea().positionCaret(index);
    }

    @FXML private void analyze(ActionEvent event) {
        currentTab.saveCodeIfRequired();
        Brainalyze analyze = new AnalyzeFactory()
            .addAnalyzers(BrainfuckAnalyzers.getAvailableAnalyzers())
            .analyze(brain(), new GroovyBFContext());
        analyze.print();
    }

    private void update() {
        currentTab.update();
	}

	private String memoryText(int i) {
		int value = brain().getMemory().getMemory(i);
		char ch = (char) (value < 0 ? 256 + value : value);
		return Integer.toString(i, 16) + "\t" + value + "\t" + String.valueOf(ch).trim() + "\t" + (brain().getMemory().getMemoryIndex() == i ? "x" : "");
	}

    private BrainfuckRunner brain() {
        return currentTab.getBrain();
    }

    @Override
	public void initialize(URL url, ResourceBundle resource) {
        FXMLLoader loader = new FXMLLoader(TabController.class.getResource("tabcontent.fxml"));
        try {
            Parent root = loader.load();
            Tab tab = new Tab("untitled1");
            tab.setContent(root);
            TabController controller = loader.getController();
            System.out.println(stage);
            controller.setStage(stage);
            currentTab = controller;
            tabs.getTabs().add(tab);
        } catch (IOException e) {
            e.printStackTrace();
            tabs.getTabs().add(new Tab(e.getMessage()));
        }
        stage.setOnCloseRequest(e -> exec.shutdownNow());
    }

    @FXML void close() {
        stage.close();
    }

}
