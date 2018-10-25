package controllers.components;


import java.util.HashMap;
import java.util.Map;


public class Board {
    Map <Integer, Enum> cardList;

    public Board() {
        cardList = new HashMap<Integer, Enum>();
        //first row
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
        //Second row
        cardList.put(10, N.C6);
        cardList.put(11, N.C5);
        cardList.put(12, N.C4);
        cardList.put(13, N.C3);
        cardList.put(14, N.C2);
        cardList.put(15, N.HA);
        cardList.put(16, N.HK);
        cardList.put(17, N.HQ);
        cardList.put(18, N.H10);
        cardList.put(19, N.S10);
        //Third row
        cardList.put(20, N.C7);
        cardList.put(21, N.SA);
        cardList.put(22, N.D2);
        cardList.put(23, N.D3);
        cardList.put(24, N.D4);
        cardList.put(25, N.D5);
        cardList.put(26, N.D6);
        cardList.put(27, N.D7);
        cardList.put(28, N.H9);
        cardList.put(29, N.SQ);
        //Fourth row
        cardList.put(30, N.C8);
        cardList.put(31, N.SK);
        cardList.put(32, N.C6);
        cardList.put(33, N.C5);
        cardList.put(34, N.C4);
        cardList.put(35, N.C3);
        cardList.put(36, N.C2);
        cardList.put(37, N.D8);
        cardList.put(38, N.H8);
        cardList.put(39, N.SK);
        //Fifth row
        cardList.put(40, N.C9);
        cardList.put(41, N.SQ);
        cardList.put(42, N.C7);
        cardList.put(43, N.H6);
        cardList.put(44, N.H5);
        cardList.put(45, N.H4);
        cardList.put(46, N.HA);
        cardList.put(47, N.D9);
        cardList.put(48, N.H7);
        cardList.put(49, N.SA);
        //Sixth row
        cardList.put(50, N.C10);
        cardList.put(51, N.S10);
        cardList.put(52, N.C8);
        cardList.put(53, N.H7);
        cardList.put(54, N.H2);
        cardList.put(55, N.H3);
        cardList.put(56, N.HK);
        cardList.put(57, N.D10);
        cardList.put(58, N.H6);
        cardList.put(59, N.D2);
        //Seventh row
        cardList.put(60, N.CQ);
        cardList.put(61, N.S9);
        cardList.put(62, N.C9);
        cardList.put(63, N.H8);
        cardList.put(64, N.H9);
        cardList.put(65, N.H10);
        cardList.put(66, N.HQ);
        cardList.put(67, N.DQ);
        cardList.put(68, N.H5);
        cardList.put(69, N.D3);
        //Eighth row
        cardList.put(70, N.CK);
        cardList.put(71, N.S9);
        cardList.put(72, N.C9);
        cardList.put(73, N.H8);
        cardList.put(74, N.H9);
        cardList.put(75, N.H10);
        cardList.put(76, N.HQ);
        cardList.put(77, N.DQ);
        cardList.put(78, N.H5);
        cardList.put(79, N.D3);
        //Ninth row
        cardList.put(80, N.CA);
        cardList.put(81, N.S7);
        cardList.put(82, N.S6);
        cardList.put(83, N.S5);
        cardList.put(84, N.S4);
        cardList.put(85, N.S3);
        cardList.put(86, N.S2);
        cardList.put(87, N.H2);
        cardList.put(88, N.H3);
        cardList.put(89, N.D5);
        //Tenth row
        cardList.put(90, N.X);
        cardList.put(91, N.DA);
        cardList.put(92, N.DK);
        cardList.put(93, N.DQ);
        cardList.put(94, N.D10);
        cardList.put(95, N.D9);
        cardList.put(96, N.D8);
        cardList.put(97, N.D7);
        cardList.put(98, N.D6);
        cardList.put(99, N.X);
    }

    public  Map <Integer, Enum> getCardList() {
        return cardList;
    }

    public Enum<N> getName(int id){
        return this.cardList.get(id);
    }
}

