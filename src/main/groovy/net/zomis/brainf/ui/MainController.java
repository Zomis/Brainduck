package net.zomis.brainf.ui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import net.zomis.brainf.BrainF;
import net.zomis.brainf.BrainFCommand;
import net.zomis.brainf.analyze.Brainalyze;
import net.zomis.brainf.model.BrainfuckRunner;

public class MainController implements Initializable {

	@FXML
	private TextArea output;
	
	@FXML
	private ListView<String> memoryList;
	
	@FXML
	private TextArea code;
	
	@FXML
	private TextArea memory;
	
	private final BrainfuckRunner brain = BrainF.createUsingSystemInputWithMemorySize(0x1000);
	
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
	private void saveCode(ActionEvent event) {
		brain.getCode().setCommands(this.code.getText());
		brain.reset();
	}
	
	@FXML
	private void runToCursor(ActionEvent event) {
		int index = code.getCaretPosition();
		while (brain.getCode().getCommandIndex() < index) {
			brain.step();
		}
		update();
	}

    @FXML
    private void analyze(ActionEvent event) {
        Brainalyze analyze = Brainalyze.analyze(brain);
        analyze.print();
    }

    @FXML
	private void step(ActionEvent event) {
		BrainFCommand comm;
		do {
			comm = brain.step();
			if (comm != BrainFCommand.NONE) {
				System.out.println("Step: " + comm);
			}
		}
		while (comm == BrainFCommand.NONE);
		
		if (Platform.isFxApplicationThread()) {
			update();
		}
		else Platform.runLater(this::update);
	}

	private void update() {
		code.selectRange(brain.getCode().getCommandIndex(), brain.getCode().getCommandIndex() + 1);
		output.setText(brain.getOutput());
		
		for (int i = 0; i < brain.getMemory().getMemorySize(); i++) {
			this.memoryList.getItems().set(i, memoryText(i));
		}
	}

	private String memoryText(int i) {
		int value = brain.getMemory().getMemory(i);
		char ch = (char) (value < 0 ? 256 + value : value);
		return Integer.toString(i, 16) + "\t" + value + "\t" + String.valueOf(ch).trim() + "\t" + (brain.getMemory().getMemoryIndex() == i ? "x" : "");
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		for (int i = 0; i < brain.getMemory().getMemorySize(); i++) {
			memoryList.getItems().add("");
		}
	}
	
}
