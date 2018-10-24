package controllers;

import controllers.window.AgileWindow;
import controllers.window.CongratulationWindow;
import controllers.window.GameWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Controller {

    private AgileWindow congratulationWindow;

    GameWindow gameWindow;

    private Stage stage;

    public Controller(){ }


    public void setStage (Stage stage) {
        this.stage = stage;
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

    /***
     * Creates a Congratulation Window when button is pressed.
     */
    @FXML public void showCongratulationWindow() {
        congratulationWindow = new CongratulationWindow();
        Label congratulation_label = new Label("You won the game!\nA big thanks for playing!\nTeam 5",
                ((CongratulationWindow) congratulationWindow).setUpImage());
        congratulation_label.setCursor(Cursor.HAND);
        congratulation_label.setTextFill(Color.BLUEVIOLET);
        congratulationWindow.setWindowWidth(400);
        congratulationWindow.setWindowHeight(300);
        congratulationWindow.setText(congratulation_label);
        congratulationWindow.show(stage);
    }

    @FXML public void closeCongratulationWindow() {
        congratulationWindow.hide();
    }

}
