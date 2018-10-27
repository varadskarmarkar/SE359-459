package controllers.components;

import java.util.*;
import javafx.geometry.Point2D;

public class CardsMngr {

    List<Card> cards;
    List<Card> boardCards;
    Board board;
    final private int scallingFactorY = 65;
    final private int scallingFactorX = 50;

    public CardsMngr() {

        cards = new ArrayList<Card>();
        board = new Board();
        // initialize card for the board.
        boardCards = new ArrayList<Card>();

    }

    public List<Card> getRandSet() {
        cards = new ArrayList<Card>();
        Random r = new Random();
        int x = r.nextInt(50);
        for (int i = 0; i < 6; i++) {
            Card card = new Card(x, new Point2D(this.scallingFactorX * i, 700), this.scallingFactorX,
                    this.scallingFactorY, board.getName(x));
            cards.add(card);
            x=r.nextInt(50);
        }
        return cards;
    }

    public List<Card> getBoardCards() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Card card = new Card((x * 10) + y,
                        new Point2D(x * scallingFactorX, y * scallingFactorY),
                        this.scallingFactorX,
                        this.scallingFactorY, board.getName((x * 10) + y));
                boardCards.add(card);
            }
        }
        return boardCards;
    }

}
