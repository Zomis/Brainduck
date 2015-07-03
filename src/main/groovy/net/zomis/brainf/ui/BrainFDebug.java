package net.zomis.brainf.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BrainFDebug extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
        primaryStage.setTitle("BrainDuck");
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
