package controllers.components;

import controllers.window.GameWindow;

// the purpose of this class it to handle the game rules and keep track of game progress like winning the game / player turns
public class GameHandler {

    private static CardsMngr cardsMngr;

    public void setCardsMngr(CardsMngr cardsMngr){
        this.cardsMngr = cardsMngr;

    }


    // the checkSequence function is supposed to check all neighbor cards and see if there is a sequence of color got formed.

    public static boolean checkSequence(Card c){
//        algorithm to check if lastly placed chip made sequence, if so , return true else false;
        return false;
    }
}
