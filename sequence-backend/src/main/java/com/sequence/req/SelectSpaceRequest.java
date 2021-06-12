package com.sequence.req;

import com.sequence.lib.Card;

public class SelectSpaceRequest implements Request {
    private int x, y;
    private int card;
    private int player;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCard() {
        return card;
    }

    public int getPlayer() {
        return player;
    }
}
