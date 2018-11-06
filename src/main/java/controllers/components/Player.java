package controllers.components;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.*;


public class Player {

    List<Card> components = new ArrayList<Card>();
    Paint color;

    public Player() {

    }

    public List<Card> getCardsOnHand(){
        return this.components;
    }

    public void setCardsOnHand(List<Card> cards) {
        this.components = cards;
    }

    public void setColor(Paint c){
        this.color = c;
    }
    public Paint getColor(){
        return this.color;
    }

//    public void addCardToHand(Card card){ this.components.add(card); }
}
