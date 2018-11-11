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
    List<Card> correctlyAnsweredCardsT1 = new ArrayList<>();
    List<Card> correctlyAnsweredCardsT2 = new ArrayList<>();
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
                                            if (players.get(currentPlayerId).getColor() == Color.GREEN){
                                                correctlyAnsweredCardsT1.add(droppedAt);
                                                GameWindow.printCardsInCorrectCardList(correctlyAnsweredCardsT1);
                                            } else {
                                                correctlyAnsweredCardsT2.add(droppedAt);
                                                GameWindow.printCardsInCorrectCardList(correctlyAnsweredCardsT2);
                                            }

                                            // here when player get it right
                                            // remove it from hand
                                            // and get the player new card
                                            //getRandCard(position of picked card)

                                        } else {
                                            System.out.println("Player id: "+currentPlayerId+" missed it");
                                        }

                                        // check for sequence
                                        // this.checkSequence(droppedAt.pos)
                                        // to loop
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

    /**
     * Julio
     * <p>
     * private void createQuestionDialog(Question q) {
     * //        QuestionGenerator questionGenerator = new QuestionGenerator();
     * ChoiceDialog choiceDialog = new ChoiceDialog(q.getAnswer(), q.getOptions());
     * choiceDialog.setHeaderText(q.getQuestion());
     * choiceDialog.show();
     * }
     * Abdul : move some of the code into the mouse handler
     */

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
//        currentHand = players.get(currentPlayerId).getCardsOnHand();
    }
    //just a debugging method
    public static void printCardsInCorrectCardList(List<Card> list){
        System.out.println(list.size());
        for (Card c : list){
            System.out.println("aye");
            System.out.print(c.getID());
        }
    }
    public void checkSequence(Point2D point){
        //algorithm to check if lastly placed chip made sequence, if so , show winning message, otherwise continue

    }
}

