package com.sequence.res;

import com.sequence.lib.Card;

import java.util.List;

public class StartGameResponse implements Response {
    private List<Card> hand;

    public StartGameResponse(List<Card> hand) {
        this.hand = hand;
    }

    public StartGameResponse() {
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
}
