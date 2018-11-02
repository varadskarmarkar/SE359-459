package controllers.components;

import java.util.*;

import javafx.geometry.Point2D;

public class CardsMngr {

    List<Card> cardsForPlayers;
    List<Card> boardCards;
    Board board;
    final private int scallingFactorY = 65;
    final private int scallingFactorX = 50;


    public CardsMngr() {


        board = new Board();

        // initialize card for the board.
        boardCards = new ArrayList<Card>();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Card card = new Card((x * 10) + y,
                        new Point2D(x * scallingFactorX, y * scallingFactorY),
                        this.scallingFactorX,
                        this.scallingFactorY, board.getName((x * 10) + y));
                boardCards.add(card);
            }
        }

        // copy cards that already initialized for board and use them to distribute cards to players hands.
        cardsForPlayers = new ArrayList<Card>();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Card card = new Card((x * 10) + y,
                        new Point2D(0,0),
                        this.scallingFactorX,
                        this.scallingFactorY, board.getName((x * 10) + y));
                cardsForPlayers.add(card);
            }
        }
    }

    public List<Card> getRandSet() {
        // create new 6 card set for player hand
        List<Card> hand = new ArrayList<Card>();

        // pick random int
        Random r = new Random();
        int x;
        // keep picking random card until we got 6 cards
        for (int i = 0; i < 6; i++) {
            x = r.nextInt(cardsForPlayers.size());
            // random card removed from the cards for player hands (like list of shuffled cards)
            Card randomCard = cardsForPlayers.remove(x);
            // update its position so it appear at the bottom (where user see it as cards on hand)
            randomCard.setPosition(this.scallingFactorX * i, 700);
            hand.add(randomCard);
        }
        return hand;
    }

    public List<Card> getBoardCards() {

        return boardCards;
    }

//    public Card getRandCard(Point2D pos){
//        Random r = new Random();
//        int x = r.nextInt(cardsForPlayers.size());
//        Card randomCard = cardsForPlayers.remove(x);
//        // update its position so it appear at the bottom (where user see it as cards on hand)
//        randomCard.move(pos.getX(),pos.getY());
//        return  randomCard;
//    }

}
