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

    public Controller() {
    }


    public void setStage(Stage stage) {
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

    /***
     * Creates a Congratulation Window when button is pressed.
     */
    @FXML
    public void showCongratulationWindow() {
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

    @FXML
    public void startGame() {
        /***
         *  Dialog box that prompts player to select number of players, returns their selection.
         */
        List<Integer> players = new ArrayList<>();

        for (int i = 2; i <= 6; i++) {
            if (i % 2 == 0) players.add(i);
        }
        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(2, players);
        dialog.setTitle("Player Count");
        dialog.setHeaderText("Set the number of people playing");
        dialog.setContentText("Number of players:");
        Optional<Integer> numOfPlayers = dialog.showAndWait();
        if (numOfPlayers.isPresent()) {
            System.out.println("Number of players selected is: " + "  " + numOfPlayers.get());
//            return numOfPlayers.get();
            gameWindow = new GameWindow();
            gameWindow.setNumOfPlayer(numOfPlayers.get());
        }

        /***
         *  Dialog box that prompts player to select game mode.
         */
        List<String> modes = new ArrayList<>();
        modes.add("Standard");
        modes.add("Monty Python");

        ChoiceDialog<String> modeDialog = new ChoiceDialog<>("Standard", modes);
        modeDialog.setTitle("Mode");
        modeDialog.setHeaderText("Select a mode");
        Optional<String> mode = modeDialog.showAndWait();
        if (mode.isPresent()) {
            System.out.println("Mode selected is: " + mode.get());
            gameWindow.setMode(mode.get());
            try {
                gameWindow.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User decide to not play :(");


        }


    }

    @FXML
    public void closeCongratulationWindow() {
        congratulationWindow.hide();
    }

}
