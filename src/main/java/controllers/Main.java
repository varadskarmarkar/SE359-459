package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("mainLayer.fxml"));
        Parent root = (Parent) loader.load();

        ((Controller) loader.getController()).setStage(primaryStage);

        Scene scene = new Scene(root, 700, 750);
        primaryStage.setTitle("Agile Game: Scrum");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
