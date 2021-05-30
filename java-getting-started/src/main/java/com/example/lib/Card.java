package com.example.lib;

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

}
