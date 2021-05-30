package com.sequence.req;

import com.sequence.lib.Card;

public class SelectCardRequest implements Request {
    private Card card;

    public Card getCard() {
        return card;
    }
}
