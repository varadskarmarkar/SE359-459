package controllers.window;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

public class BoardWindow extends Popup {

    final private int scallingFactorY = 65;
    final private int scallingFactorX = 50;
    final private int boardSize = 10;

    Pane board;

    public BoardWindow(){

        board = new AnchorPane();
        // Scene(Parent root, width, height)
//        scene = new Scene(root, (boardSize * scallingFactorX), (boardSize * scallingFactorY));
//        primaryStage.setTitle("Agile Sequence Board Game");
//        primaryStage.setScene(scene);
//        primaryStage.show();

        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {

                Rectangle rect = new Rectangle(x * scallingFactorX, y * scallingFactorY , this.scallingFactorX, this.scallingFactorY);
                rect.setStroke(Color.BLACK); // We want the black outline

                rect.setFill(Color.LIGHTBLUE);
                board.getChildren().add(rect);
            }
        }
        super.getContent().addAll(board);

    }
}
