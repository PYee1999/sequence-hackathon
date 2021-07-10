package com.sequence.req;

import com.sequence.lib.Card;

public class SelectCardRequest implements Request {
    private int player;
    private int cardID;
    private int cardSuitNum;
    private int coordinates[][];

    public int getCardID() {
        return cardID;
    }

    public int getCardSuitNum() {
        return cardSuitNum;
    }

    public int getPlayer() {
        return player;
    }

    public int[][] getCoordinates() {
        return coordinates;
    }
}
