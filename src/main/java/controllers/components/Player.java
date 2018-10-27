package controllers.components;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.layout.*;

public class Player {

    List<Card> components = new ArrayList<Card>();

    public Player() {

    }

    public List<Card> getCardsOnHand(){
        return this.components;
    }

    public void setCardsOnHand(List<Card> cards) {
        this.components = cards;
    }
}
