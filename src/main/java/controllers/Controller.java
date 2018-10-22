package controllers;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import window.AgileWindow;
import window.BoardWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import window.CongratulationWindow;


public class Controller {

    private AgileWindow congratulationWindow;
    private BoardWindow bWindow;

    /**
     *  Made "Stage" be a field here so that we can access it better within the controller once
     * the controller knows about it after using its setter method.
     */
    private Stage stage;

    public Controller() { }

    public void setStage (Stage stage) {
        this.stage = stage;
    }


    @FXML
    protected void closeCongratulationWindow(ActionEvent event) {
        congratulationWindow.hide();
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

    @FXML public void showBoard(ActionEvent event) {
        bWindow = new BoardWindow();
        bWindow.show(this.stage);
        System.out.println("start game pressed");
    }

    @FXML public void closeBoard(ActionEvent actionEvent) {
        bWindow.hide();
        System.out.println("Close Board pressed");
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
}
