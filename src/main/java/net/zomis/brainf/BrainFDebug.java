package net.zomis.brainf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.zomis.brainf.ui.MainController;

public class BrainFDebug extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
        primaryStage.setTitle("BrainF");
        Parent root = FXMLLoader.load(MainController.class.getResource("brainf.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	
	
	
}
