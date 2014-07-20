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

public class MainController implements Initializable {

	@FXML
	private TextArea output;
	
	@FXML
	private ListView<String> memoryList;
	
	@FXML
	private TextArea code;
	
	@FXML
	private TextArea memory;
	
	private final BrainF brain = new BrainF(System.in);
	
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
		brain.setCommands(this.code.getText());
		brain.reset();
	}
	
	@FXML
	private void runToCursor(ActionEvent event) {
		int index = code.getCaretPosition();
		while (brain.getCommandIndex() < index) {
			brain.step();
		}
		update();
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
		memory.setText(memoryText(brain));
		code.selectRange(brain.getCommandIndex(), brain.getCommandIndex() + 1);
		output.setText(brain.getOutput());
		
		for (int i = 0; i < brain.getMemorySize(); i++) {
			this.memoryList.getItems().set(i, memoryText(i));
		}
	}

	private String memoryText(int i) {
		int value = brain.getMemory(i);
		char ch = (char) (value < 0 ? 256 + value : value);
		return Integer.toString(i, 16) + "\t" + value + "\t" + String.valueOf(ch).trim() + "\t" + (brain.getMemoryIndex() == i ? "x" : "");
	}

	private String memoryText(BrainF brain2) {
		StringBuilder str = new StringBuilder();
		
		for (int i = 0; i < brain2.getMemorySize(); i++) {
			byte mem = brain2.getMemory(i);
			
			str.append(Integer.toString(i, 16));
			str.append("\t");
			str.append(mem);
			if (brain2.getMemoryIndex() == i) {
				str.append(" <---");
			}
			str.append("\n");
		}
		
		return str.toString();
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		for (int i = 0; i < brain.getMemorySize(); i++) {
			memoryList.getItems().add("");
		}
	}
	
}
