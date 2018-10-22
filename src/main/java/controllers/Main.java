package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

     BorderPane root;
     Scene scene;
     Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("mainLayer.fxml"));
        root = loader.load();
        Controller mainController =  loader.getController();
        mainController.setStage(this.stage);



        scene = new Scene(root, 700, 750);
        this.stage.setTitle("Agile Game: Scrum");
        this.stage.setScene(scene);
        this.stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
