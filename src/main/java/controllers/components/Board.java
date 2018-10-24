package controllers.components;


import java.util.HashMap;
import java.util.Map;


public class Board {
    Map<Integer, Enum> cardList;

    public Board() {
        cardList = new HashMap<Integer, Enum>();
        cardList.put(0, N.X);
        cardList.put(1, N.S2);
        cardList.put(2, N.S3);
        cardList.put(3, N.S4);
        cardList.put(4, N.S5);
        cardList.put(5, N.S6);
        cardList.put(6, N.S7);
        cardList.put(7, N.S8);
        cardList.put(8, N.S9);
        cardList.put(9, N.X);
    }
}

