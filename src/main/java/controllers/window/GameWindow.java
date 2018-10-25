package controllers.window;

import java.util.List;

import controllers.components.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameWindow extends Application {
    final private int scallingFactorY = 65;
    final private int scallingFactorX = 50;
    final private int boardSize = 10;

    Pane root;
    Scene scene;
    Point2D lastPosition;
    boolean inDragMode = false;
    Card currentComponent;

    List<Card> boardCards;
    List<Card> currentPlayerCard;


    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        root = new Pane();

        scene = new Scene(root, 600, 800, Color.LIGHTGREEN);
        primaryStage.setTitle("Game");

        // Event filters.

        root.setOnMouseDragged(mouseHandler);
        root.setOnMouseReleased(mouseHandler);
        root.setOnMousePressed(mouseHandler);



        CardsMngr cards = new CardsMngr();

        boardCards = cards.getBoardCards();

        //First Player
        Player player = new Player();
        player.setCardsOnHand(cards.getRandSet());
        this.currentPlayerCard = player.getCardsOnHand();

        root.getChildren().addAll(boardCards);
        root.getChildren().addAll(currentPlayerCard);

        primaryStage.setScene(scene);
        primaryStage.show();

    }


    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Point2D clickPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());

            String eventName = mouseEvent.getEventType().getName();
            if (!inDragMode)
                if (currentComponent == null) {
                    try {
                        currentComponent = getCurrentShape(currentPlayerCard, clickPoint);
                    } catch (NullPointerException ex) {
                        System.out.println("trying to pick no card!");
                    }

                }
            switch (eventName) {

                case ("MOUSE_RELEASED"):

                    Point2D lpos = new Point2D(mouseEvent.getX(), mouseEvent.getY());
                    Card droppedAt = getCurrentShape(boardCards, lpos);
                    if (currentComponent != null) {
                        if (droppedAt == null) {
                            System.out.println("cannot find card");
                        }
                        else {
                            if (currentComponent.getID() == droppedAt.getID()) {
                                createQuestionDialog();
                                droppedAt.placeChip();
                            }
                            System.out.println(droppedAt.getID());
                        }

                        currentComponent.setOrignalPos();
                    }
                    currentComponent = null;
                    inDragMode = false;
                    break;

                case ("MOUSE_DRAGGED"):
                    inDragMode = true;
                    if (currentComponent != null && lastPosition != null) {
                        double delateX = clickPoint.getX() - lastPosition.getX();
                        double delateY = clickPoint.getY() - lastPosition.getY();
                        currentComponent.move(delateX, delateY);
                    }

                    break;

            }
            lastPosition = clickPoint;

        }

    };

    /**
     * Julio
     */
    private void createQuestionDialog() {
        QuestionGenerator questionGenerator = new QuestionGenerator();
        ChoiceDialog choiceDialog = new ChoiceDialog<>("Select all that apply");
        choiceDialog.setHeaderText(questionGenerator.getRandomQuestion());
        choiceDialog.show();
    }

    public Card getCurrentShape(List<Card> components, Point2D clickLocation) {
        for (Card component : components)
            if (component.contains(clickLocation)) {
                if (component instanceof Card)
                    return component;
            }

        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

