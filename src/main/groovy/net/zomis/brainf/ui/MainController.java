package net.zomis.brainf.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.zomis.brainf.BrainF;
import net.zomis.brainf.BrainFCommand;
import net.zomis.brainf.analyze.Brainalyze;
import net.zomis.brainf.model.BrainfuckRunner;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

public class MainController implements Initializable {

    @FXML private TabPane tabs;

    private TabController currentTab;
    private Stage stage;

    public MainController() {
	}
	
	private ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	
	@FXML
	private void repeat(ActionEvent event) {
		System.out.println("Repeat");
//		for (int i = 0; i < 100; i++) {
//			brain.step();
//		}
		exec.scheduleWithFixedDelay(() -> this.step(null), 100, 100, TimeUnit.MILLISECONDS);
	}
	@FXML
	private void runToCursor(ActionEvent event) {
		int index = currentTab.getCodeArea().getCaretPosition();
		while (brain().getCode().getCommandIndex() < index) {
			brain().step();
		}
		update();
	}

    @FXML
	private void step(ActionEvent event) {
        BrainFCommand comm;
        boolean repeat = true;
        while (repeat) {
            comm = brain().step();
            if (comm != BrainFCommand.NONE) {
                System.out.println("Step: " + comm);
            }
            repeat = comm == BrainFCommand.NONE;
        }

        if (Platform.isFxApplicationThread()) {
            update();
        }
        else Platform.runLater(() -> update());
	}

    @FXML private void analyze(ActionEvent event) {
        Brainalyze analyze = Brainalyze.analyze(brain());
        analyze.print();
    }

    private void update() {
		currentTab.getCodeArea().selectRange(brain().getCode().getCommandIndex(), brain().getCode().getCommandIndex() + 1);
		currentTab.getOutput().setText(brain().getOutput());

		for (int i = 0; i < brain().getMemory().getMemorySize(); i++) {
			currentTab.getMemoryList().getItems().set(i, memoryText(i));
		}
	}

	private String memoryText(int i) {
		int value = brain().getMemory().getMemory(i);
		char ch = (char) (value < 0 ? 256 + value : value);
		return Integer.toString(i, 16) + "\t" + value + "\t" + String.valueOf(ch).trim() + "\t" + (brain().getMemory().getMemoryIndex() == i ? "x" : "");
	}

    @FXML private void saveCode(ActionEvent event) {
        brain().getCode().setCommands(currentTab.getCodeArea().getText());
        brain().reset();
    }

    private BrainfuckRunner brain() {
        return currentTab.getBrain();
    }

    @Override
	public void initialize(URL url, ResourceBundle resource) {
        FXMLLoader loader = new FXMLLoader(TabController.class.getResource("tabcontent.fxml"));
        try {
            Parent root = loader.load();
            TabController controller = loader.getController();
//            controller.initStage(primaryStage);
            Tab tab = new Tab("untitled1");
            tab.setContent(root);
            currentTab = controller;
            tabs.getTabs().add(tab);
        } catch (IOException e) {
            e.printStackTrace();
            tabs.getTabs().add(new Tab(e.getMessage()));
        }
	}

    public void initStage(Stage stage) {
        this.stage = stage;
    }

}
