package controllers.components;

import java.util.*;
import javafx.geometry.Point2D;

public class CardsMngr {

    List<Card> cards;
    List<Card> boardCards;
    Board board;
    final private int scalingFactorY = 65;
    final private int scalingFactorX = 50;

    public CardsMngr() {

        cards = new ArrayList<Card>();
        board = new Board();
        // initialize card for the board.
        boardCards = new ArrayList<Card>();

    }

    public List<Card> getRandSet() {
        ArrayList<Card> playerHand = new ArrayList<Card>(); //create list for 6 cards to be dealt
        cards = this.getBoardCards(); //get list of cards already created
        Random r = new Random();
        int cardsSize = cards.size();
        int x = r.nextInt(cardsSize);//set random pool size to number of cards
        for (int i = 0; i < 6; i++) {
            Card tempCard = cards.get(x);//get card by randomly generated index
            tempCard.setPosition(this.scalingFactorX *i, 700); //set position
            playerHand.add(tempCard); //add to player's hand
            cards.remove(tempCard); //remove from list of remaining cards
            cardsSize--; //decrement size and then regenerate random number
            x=r.nextInt(cardsSize);
        }
        return cards;
    }

    public List<Card> getBoardCards() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Card card = new Card((x * 10) + y,
                        new Point2D(x * scalingFactorX, y * scalingFactorY),
                        this.scalingFactorX,
                        this.scalingFactorY, board.getName((x * 10) + y));
                boardCards.add(card);
            }
        }
        return boardCards;
    }

}
