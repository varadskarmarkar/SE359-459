package controllers.components;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.layout.*;

public class Player extends Pane {
    final private int scallingFactorY = 65;
    final private int scallingFactorX = 50;
    Point2D lastPosition;
    boolean inDragMode = false;
    Card currentComponent = null;
    List<Card> components = new ArrayList<Card>();

    public Player() {
        this.setWidth(6*this.scallingFactorX);
        this.setHeight(scallingFactorY);



    }

    public List<Card> getCardsOnHand(){
        return this.components;
    }

    public void setCardsOnHand(List<Card> cards) {
        getChildren().addAll(components = cards);
    }
}
