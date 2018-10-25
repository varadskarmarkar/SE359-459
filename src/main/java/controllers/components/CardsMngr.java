package controllers.components;

import java.util.*;
import javafx.geometry.Point2D;

public class CardsMngr {

    List<Card> cards;
    List<Card> boardCards;
    Board board;
    final private int scallingFactorY = 65;
    final private int scallingFactorX = 50;

    // Julio
    private Board gameBoard = new Board();
    private final int AMOUNT_OF_CARDS_ON_THE_BOARD =  gameBoard.cardList.keySet().size();



    public CardsMngr() {

        cards = new ArrayList<Card>();
        board = new Board();
        // initialize card for the board.
        boardCards = new ArrayList<Card>();


    }

    public List<Card> getRandSet() {
        for (int i = 0; i < 6; i++) {
//            Card card = new Card(i, new Point2D(this.scallingFactorX * i, 700), this.scallingFactorX,
//                    this.scallingFactorY, N.X);
//            cards.add(card);
//            int rand_IDs_from_board = new Random().nextInt(100);
            /**
             * Julio
             *
             */
            int rand_IDs_from_board = new Random().nextInt(AMOUNT_OF_CARDS_ON_THE_BOARD);
            Card card = new Card(
              rand_IDs_from_board, new Point2D(this.scallingFactorX * i, 700), this.scallingFactorX, this.scallingFactorY,
                    generateLayoutForCard()
            );
            cards.add(card);

        }
        return cards;
    }


    /**
     * Julio
     */
    private Enum generateLayoutForCard() {
        List<N> allCardsLayout = Arrays.asList(N.values());
        int amountOfCardsLayout = new Random().nextInt(allCardsLayout.size());
        return allCardsLayout.get(amountOfCardsLayout);
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
