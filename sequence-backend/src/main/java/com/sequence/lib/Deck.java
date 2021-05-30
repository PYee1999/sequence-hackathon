package com.sequence.lib;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {
    private LinkedList<Card> cards;
    private int cardNum;

    public void initDeck() {
        cards = new LinkedList<>();
        cardNum = 104;
        for (int i = 0; i < 104; i++) {
            cards.add(new Card(i, calcCardSuitNum(i)));
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        cardNum--;
        return cards.poll();
    }

    public int getCardNum() {
        return cardNum;
    }

    private int calcCardSuitNum(int cardID) {
        cardID = cardID % 52;
        int suit = cardID / 13 + 1;
        int rank = cardID % 13 + 1;
        return suit * 100 + rank;
    }
}
