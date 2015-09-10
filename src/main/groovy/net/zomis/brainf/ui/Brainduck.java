package net.zomis.brainf.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Brainduck extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
        primaryStage.setTitle("BrainDuck");
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("brainf.fxml"));
        MainController controller = new MainController(primaryStage);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(MainController.class.getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	
	
	
}
