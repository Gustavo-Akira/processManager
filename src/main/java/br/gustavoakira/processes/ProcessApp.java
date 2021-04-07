package br.gustavoakira.processes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ProcessApp extends Application{
	

	@Override
	public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
		var scene = new Scene(new StackPane(),640, 480);
		stage.setScene(scene);
		scene.setRoot(root);
        stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
