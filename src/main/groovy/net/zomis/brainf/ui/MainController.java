package net.zomis.brainf.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.zomis.brainf.model.BrainfuckRunner;
import net.zomis.brainf.model.run.*;
import org.controlsfx.control.StatusBar;
import org.controlsfx.control.TaskProgressView;

public class MainController implements Initializable {

    @FXML private TabPane tabs;
    @FXML private StatusBar statusBar;
    TaskProgressView<BFTask> tasks = new TaskProgressView<>();

    private Stage stage;
    private final Map<Tab, TabController> tabMap = new HashMap<>();
    private final FileChooser openDialog = new FileChooser();

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
        currentTab().saveCodeIfRequired();
		int index = currentTab().getCodeArea().getCaretPosition();
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
        currentTab().stopRun();
    }

    private void runWith(RunStrategy strategy) {
        currentTab().run(exec, strategy);
    }

    @FXML private void newTab(ActionEvent event) {
        createTab("untitled");
        tabs.getSelectionModel().selectLast();
    }

    @FXML private void openFile(ActionEvent event) {
        File file = openDialog.showOpenDialog(stage);
        if (file != null) {
            TabController tab = createTab(file.getName());
            tab.setCode(GroovyRead.file(file));
            tab.getLoadSave().setFile(file);
            tab.getLoadSave().notModified();
            tabs.getSelectionModel().selectLast();
        }
    }

    @FXML private void saveFile(ActionEvent event) {
        currentTab().getLoadSave().save(stage, currentTab().getCode());
    }

    @FXML private void closeTab(ActionEvent event) {
        if (currentTab().getLoadSave().closeRequest(stage, currentTab().getCode())) {
            Tab selected = tabs.getTabs().remove(tabs.getSelectionModel().getSelectedIndex());
            tabMap.remove(selected);
        }
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
        String code = currentTab().getCodeArea().getText();
        int index = currentTab().getCodeArea().getCaretPosition();
        currentTab().setupCodeArea();
        currentTab().getCodeArea().replaceText(code);
        currentTab().getCodeArea().positionCaret(index);
    }

    @FXML private void analyze(ActionEvent event) {
        currentTab().performAnalyze();
    }

    private void update() {
        currentTab().update();
	}

	private String memoryText(int i) {
		int value = brain().getMemory().getMemory(i);
		char ch = (char) (value < 0 ? 256 + value : value);
		return Integer.toString(i, 16) + "\t" + value + "\t" + String.valueOf(ch).trim() + "\t" + (brain().getMemory().getMemoryIndex() == i ? "x" : "");
	}

    private BrainfuckRunner brain() {
        return currentTab().getBrain();
    }

    public TabController createTab(String tabTitle) {
        FXMLLoader loader = new FXMLLoader(TabController.class.getResource("tabcontent.fxml"));
        try {
            Parent root = loader.load();
            Tab tab = new Tab(tabTitle);
            tab.setContent(root);
            TabController controller = loader.getController();
            System.out.println(stage);
            controller.setup(tab, stage, this);
            tabMap.put(tab, controller);
            tabs.getTabs().add(tab);
            return controller;
        } catch (IOException e) {
            e.printStackTrace();
            tabs.getTabs().add(new Tab(e.getMessage()));
            return null;
        }
    }

    @FXML void close() {
        stage.close();
    }

    public void setCurrentTab(TabController tab) {
        if (tab == null) {
            return;
        }
        tabs.getSelectionModel().select(tab.getTab());
    }

    public TabController currentTab() {
        Tab selected = tabs.getSelectionModel().getSelectedItem();
        return tabMap.get(selected);
    }

    public void initStage(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setOnCloseRequest(e -> {
            boolean closeAllTabs = tabMap.values().stream().allMatch(tc -> tc.getLoadSave().closeRequest(stage, tc.getCode()));
            if (closeAllTabs) {
                exec.shutdownNow();
            } else {
                e.consume();
            }
        });
        TabController tab = createTab("untitled1");
        tab.setCode(GroovyRead.read("fizzbuzz.bf"));
        tab.getLoadSave().notModified();
    }

    @FXML void aboutDialog(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Properties versionInfo = new Properties();
        String version = "???";
        String branch = "???";
        String hash = "????????";
        String date = "???";
        String exception = null;
        try {
            versionInfo.load(getClass().getClassLoader().getResourceAsStream("version.dat"));
            version = versionInfo.getProperty("version");
            branch = versionInfo.getProperty("branch");
            hash = versionInfo.getProperty("commit");
            date = versionInfo.getProperty("date");
        } catch (IOException e) {
            exception = e.getLocalizedMessage();
        }
        alert.setTitle("Brainduck " + versionInfo.getProperty("version"));
        alert.setHeaderText(alert.getTitle());
        alert.setContentText("Version " + version +
                "\nBuilt on " + date +
                "\nBuilt from branch " + branch +
                "\nCommit hash id " + hash.substring(0, 8) +
                (exception != null ? "\n" + exception : "") + "\n\nhttps://github.com/Zomis/Brainduck");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusBar.setOnMouseClicked(e -> {
            Stage st = new Stage();
            Scene sc = new Scene(tasks);
            st.setScene(sc);
            st.centerOnScreen();
            st.show();
        });
        statusBar.setText("Hello World");
    }

    public void taskStarted(BFTask task) {
        tasks.getTasks().add(task);
        statusBar.progressProperty().bind(task.progressProperty());
        statusBar.textProperty().bind(task.messageProperty());
    }

}
