package com.sequence.res;

import com.sequence.lib.Card;

import java.util.List;

public class DeadCardResponse implements Response {
    private boolean cardRemoved;
    private Card oldCard, newCard;
    private List<Card> hand;

    public DeadCardResponse() {
    }

    public DeadCardResponse(boolean cardRemoved) {
        this.cardRemoved = cardRemoved;
    }

    public DeadCardResponse(boolean cardRemoved, Card oldCard, Card newCard, List<Card> hand) {
        this.cardRemoved = cardRemoved;
        this.oldCard = oldCard;
        this.newCard = newCard;
        this.hand = hand;
    }

    public boolean isCardRemoved() {
        return cardRemoved;
    }

    public void setCardRemoved(boolean cardRemoved) {
        this.cardRemoved = cardRemoved;
    }

    public Card getOldCard() {
        return oldCard;
    }

    public void setOldCard(Card oldCard) {
        this.oldCard = oldCard;
    }

    public Card getNewCard() {
        return newCard;
    }

    public void setNewCard(Card newCard) {
        this.newCard = newCard;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
}
