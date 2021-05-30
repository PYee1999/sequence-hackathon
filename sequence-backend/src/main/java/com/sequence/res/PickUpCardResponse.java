package com.sequence.res;

import com.sequence.lib.Card;

public class PickUpCardResponse implements Response {
    private Card newCard;
    private Card[] hand;

    public PickUpCardResponse() {}

    public PickUpCardResponse(Card newCard, Card[] hand) {
        this.newCard = newCard;
        this.hand = hand;
    }

    public Card getNewCard() {
        return newCard;
    }

    public void setNewCard(Card newCard) {
        this.newCard = newCard;
    }

    public Card[] getHand() {
        return hand;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }
}
