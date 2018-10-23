package controllers;

import controllers.window.AgileWindow;
import controllers.window.GameWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Controller {
    private AgileWindow window;
    GameWindow gameWindow;
    private Stage stage;

    public Controller(){
        window = new AgileWindow();

        // Set the message area properties
        Label welcome_message = new Label();
        welcome_message.setFont(Font.font("Times New Roman"));
        welcome_message.setCursor(Cursor.HAND);
        welcome_message.setTextFill(Color.BLUEVIOLET);
        welcome_message.setText("Welcome to our Agile game! \n We hope you enjoy it!");

        // Set the pop-up window properties
        window.setText(welcome_message);
        window.setWindowWidth(260);
        window.setWindowHeight(200);
    }

    public void setStage (Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void openPopup(ActionEvent event) {
        window.show(this.stage);
        System.out.println("openPopup pressed");
    }

    @FXML
    protected void closePopup(ActionEvent event) {
        window.hide();
        System.out.println("closePopup pressed");
    }

    @FXML
    protected void correctAnswerAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.NONE, "Good job! You are correct!", ButtonType.CLOSE);
        alert.showAndWait();
    }

    @FXML
    protected void incorrectAnswerAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.NONE, "Sorry, your answer was incorrect.", ButtonType.CLOSE);
        alert.showAndWait();
    }

    public void startGame(ActionEvent event) {
        gameWindow = new GameWindow();
        try {
            gameWindow.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("start game pressed");
    }

}
