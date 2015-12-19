package net.zomis.brainf.ui;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import net.zomis.brainf.model.*;
import net.zomis.brainf.model.input.ConsoleInput;
import net.zomis.brainf.model.input.ConsoleOutput;

import java.io.File;

public class Brainduck extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
        primaryStage.setTitle("BrainDuck");
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("brainf.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.initStage(primaryStage);
        Scene scene = new Scene(root);
        scene.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
        });

        scene.setOnDragDropped(event -> {
            Dragboard dragBoard = event.getDragboard();
            if (dragBoard.hasFiles()) {
                TabController tab = null;
                for (File file : dragBoard.getFiles()) {
                    tab = controller.createTab(file.getName());
                    if (tab != null) {
                        tab.getLoadSave().setFile(file);
                        tab.setCode(GroovyRead.file(file));
                        tab.getLoadSave().notModified();
                    }
                }
                controller.setCurrentTab(tab);
            }
            event.setDropCompleted(dragBoard.hasFiles());
            event.consume();
        });
        scene.getStylesheets().add(MainController.class.getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
		
	}

	public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else {
            CommandLineOptions options = new CommandLineOptions();
            JCommander jcommander = new JCommander(options);
            try {
                jcommander.parse(args);
            } catch (ParameterException ex) {
                System.out.println(ex.getMessage());
                jcommander.usage();
                return;
            }
            for (String file : options.main) {
                BrainfuckCode code = new BrainfuckCode();
                code.setSource(ListCode.create(GroovyRead.file(new File(file))));
                BrainfuckRunner runner = new BrainfuckRunner(new BrainfuckMemory(30000), code,
                        new ConsoleInput(), new ConsoleOutput());
                runner.run();
            }
        }
	}

	
	
	
}
