package controllers;

import controllers.window.AgileWindow;
import controllers.window.CongratulationWindow;
import controllers.window.GameWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Controller {

    private AgileWindow congratulationWindow;

    GameWindow gameWindow;
    @FXML
    Button welcomeMessage;

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

    @FXML public int setWelcomeMessage(){
        /***
         *  Dialog box that prompts player to select number of players, returns their selection.
         */
        List<Integer> players = new ArrayList<>();
        for(int i = 2; i <= 6;i++){
            players.add(i);
        }
        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(2,players);
        dialog.setTitle("Player Count");
        dialog.setHeaderText("Set the number of people playing");
        dialog.setContentText("Number of players:");
        Optional<Integer> numOfPlayers = dialog.showAndWait();
        if(numOfPlayers.isPresent()){
            System.out.println("number of players selected is: " + "  " + numOfPlayers.get());
            return numOfPlayers.get();
        }
        else{
            System.out.println("User did not select anything, default player count of 2 will be used.");
            return 2;
        }


    }
    @FXML public void closeCongratulationWindow() {
        congratulationWindow.hide();
    }

}
