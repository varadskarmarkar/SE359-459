package controllers.window;

import controllers.components.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameWindow extends Application {

    final private int scallingFactorY = 65;
    final private int scallingFactorX = 50;
    final private int boardSize = 10;
    private String mode = "";
    Pane root;
    Scene scene;
    int numOfPlayer;
    int currentPlayerId = 0;
    Point2D lastPosition; // for dragging and calculating the delta
    boolean inDragMode = false;
    Card pickedCard; // the one being picked from player's hand, and dragged
//    List<Card> currentHand;
    List<Player> players = new ArrayList<Player>();
    QuestionsMngr questionsMngr;
    CardsMngr cardsMngr = new CardsMngr();
    List<Card> boardCards = cardsMngr.getBoardCards();
    List<Integer> correctlyAnsweredCardsT1 = new ArrayList<>();
    List<Integer> correctlyAnsweredCardsT2 = new ArrayList<>();
    Button b;

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Point2D clickPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());

                String eventName = mouseEvent.getEventType().getName();
                if (!inDragMode)
                    if (pickedCard == null) {
                        try {
                            pickedCard = getCurrentShape(players.get(currentPlayerId).getCardsOnHand(), clickPoint);
                        } catch (NullPointerException ex) {
                            System.out.println("trying to pick no card!");
                        }

                    }
                switch (eventName) {

                    case ("MOUSE_RELEASED"):

                        Point2D lpos = new Point2D(mouseEvent.getX(), mouseEvent.getY());

                        Card droppedAt = getCurrentShape(boardCards, lpos);
                        if (pickedCard != null) {
                            if (droppedAt == null) {
                                System.out.println("cannot find card");
                            } else if (droppedAt.checkIfTaken()) {
                                System.out.println("Space is taken; find another spot.");
                            } else {
                                if (pickedCard.getName() == droppedAt.getName()) {


                                    Question q = questionsMngr.getNextQuestion();

                                    ChoiceDialog choiceDialog;
                                    try {
                                        choiceDialog = new ChoiceDialog(q.getAnswer(), q.getOptions());
                                        choiceDialog.setTitle("Question"); // added this so that the title of the window isn't "Confirmation"
                                        choiceDialog.setHeaderText(q.getQuestion());

                                        choiceDialog.showAndWait();


                                        if (choiceDialog.getResult().equals(q.getAnswer())) {
                                            System.out.println("Player id: "+currentPlayerId+" got it");
                                            droppedAt.placeChip(players.get(currentPlayerId).getColor());
                                            droppedAt.setTaken();
                                            if (players.get(currentPlayerId).getColor() == Color.GREEN){
                                                correctlyAnsweredCardsT1.add(droppedAt.getID());
                                                System.out.println("Added to T1 cards: " + droppedAt.getID());
//                                                ArrayList<Integer> testList = new ArrayList<>();
//                                                testList.add(58);
//                                                testList.add(68);
//                                                testList.add(78);
//                                                testList.add(88);
//                                                testList.add(90);
//                                                System.out.println("testing if testlist is a sequence: " + checkHorizontal(testList));
                                                System.out.println("testing if it's a sequence: " + checkVertical(correctlyAnsweredCardsT1));
                                                checkVertical(correctlyAnsweredCardsT1);
                                                checkHorizontal(correctlyAnsweredCardsT1);
                                            } else {
                                                correctlyAnsweredCardsT2.add(droppedAt.getID());
                                                System.out.println("Added to T2 cards: " + droppedAt.getID());
                                                System.out.println("testing if it's a sequence: " + checkVertical(correctlyAnsweredCardsT2));
                                                checkVertical(correctlyAnsweredCardsT2);
                                                checkHorizontal(correctlyAnsweredCardsT2);
                                            }

                                            // here when player get it right
                                            // remove it from hand.
                                            // and get the player new card
                                            //getRandCard(position of picked card)

                                        } else {
                                            System.out.println("Player id: "+currentPlayerId+" missed it");
                                        }

                                        root.getChildren().removeAll(players.get(currentPlayerId).getCardsOnHand());
                                        currentPlayerId++;
                                        currentPlayerId = currentPlayerId%numOfPlayer;
                                        root.getChildren().addAll(players.get(currentPlayerId).getCardsOnHand());
                                        b.setText("I am player id: "+currentPlayerId);
                                        root.getChildren().add(b);


                                    } catch (NullPointerException ne) {
                                        System.out.println("Dialog close");
                                    }

                                }

                                System.out.println(droppedAt.getID());
                            }

                            pickedCard.setOrignalPos();
                        }
                        pickedCard = null;
                        inDragMode = false;
                        break;

                    case ("MOUSE_DRAGGED"):
                        inDragMode = true;
                        if (pickedCard != null && lastPosition != null) {
                            double delateX = clickPoint.getX() - lastPosition.getX();
                            double delateY = clickPoint.getY() - lastPosition.getY();
                            pickedCard.move(delateX, delateY);
                        }

                        break;

                }
                lastPosition = clickPoint;

            }

        };



    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        root = new Pane();

        scene = new Scene(root, 600, 800);
        primaryStage.setTitle("Game");

        // Event filters.

        root.setOnMouseDragged(mouseHandler);
        root.setOnMouseReleased(mouseHandler);
        root.setOnMousePressed(mouseHandler);


        primaryStage.setScene(scene);
        primaryStage.show();


//
//        boardCards = cardsMngr.getBoardCards();
//
//        //First Player
//        Player player = new Player();
//        player.setCardsOnHand(cardsMngr.getRandSet());
//        this.currentPlayerCard = player.getCardsOnHand();

        root.getChildren().addAll(boardCards);
        root.getChildren().addAll(players.get(currentPlayerId).getCardsOnHand());
        b = new Button("I am player id: "+currentPlayerId);

        b.setLayoutY(700);
        b.setPrefWidth(scallingFactorX*6);
        b.setPrefHeight(scallingFactorY);
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().remove(b);
            }
        });
        root.getChildren().add(b);

    }

    public Card getCurrentShape(List<Card> components, Point2D clickLocation) {
        for (Card component : components)
            if (component.contains(clickLocation)) {
                if (component instanceof Card)
                    return component;
            }

        return null;
    }

    public void setMode(String mode) {
        this.mode = mode;
        System.out.println("Mode is " + mode);
        questionsMngr = new QuestionsMngr(mode);
    }

    public void setNumOfPlayer(int numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
        for ( int i = 0 ; i < numOfPlayer; i++){
            Player p = new Player();
            p.setColor(((i%2==0)? Color.GREEN : Color.BLUE));
            p.setCardsOnHand(cardsMngr.getRandSet());
            players.add(p);

        }
    }

    // this function is for the test that Abdul wrote. I updated the code to use checkVertical and checkHorizontal directly.
    public void checkSequence(Point2D point){
        checkVertical(correctlyAnsweredCardsT1);
        checkHorizontal(correctlyAnsweredCardsT1);
        checkVertical(correctlyAnsweredCardsT2);
        checkHorizontal(correctlyAnsweredCardsT2);
    }

    // vertical check
    private static boolean checkVertical(List<Integer> teamCards) {
        int columnAdditionFactor = 0;
        int targetNumber;
        for (int j = 0; j < 10 ; j++ ) {
            for (int i = 0; i < 6; i++) {
                targetNumber = i + columnAdditionFactor;
                if (teamCards.contains(targetNumber) && teamCards.contains(targetNumber + 1) &&
                        teamCards.contains(targetNumber + 2) && teamCards.contains(targetNumber + 3) &&
                        teamCards.contains(targetNumber + 4)) {
                    return true;
                }
            }
            columnAdditionFactor += 10;

        }
        return false;
    }

    //horizontal check
    private static boolean checkHorizontal(List<Integer> teamCards) {
        int rowAdditionFactor = 0;
        int targetNumber;
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 6; i++) {
                targetNumber= (i * 10) + rowAdditionFactor;
                if (teamCards.contains(targetNumber) && teamCards.contains(targetNumber + 10) &&
                        teamCards.contains(targetNumber + 20) && teamCards.contains(targetNumber + 30) &&
                        teamCards.contains(targetNumber + 40)) {
                    return true;
                }
            }
            rowAdditionFactor++;
        }
        return false;
    }
}

