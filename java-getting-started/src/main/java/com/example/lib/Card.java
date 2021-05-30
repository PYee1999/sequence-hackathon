package com.example.lib;

import java.util.Objects;

public class Card {

    private int cardID;         // Unique Id for Card
    private int cardSuitNum; // Suit & Number for card

    public Card(int cardID, int cardSuitNum) {
        this.cardID = cardID;
        this.cardSuitNum = cardSuitNum;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getCardSuitNum() {
        return cardSuitNum;
    }

    public void setCardSuitNum(int cardSuitNum) {
        this.cardSuitNum = cardSuitNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardID == card.cardID && cardSuitNum == card.cardSuitNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardID, cardSuitNum);
    }
}
