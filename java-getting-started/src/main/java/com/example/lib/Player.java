package com.example.lib;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int playerMarker;
    private int sequenceCounter;
    private List<Space> sequenceList;
    private List<Card> cardsList;
    private Board board;
    private Deck deck;

    // playerColor: 1 is red, -1 is blue
    public Player(Board board, Deck deck, int playerColor) {
        this.board = board;
        this.deck = deck;
        playerMarker = playerColor;
        sequenceCounter = 0;
        cardsList = new ArrayList<>();
    }

    public void addCard(Card card) {
        cardsList.add(card);
    }

    public int getPlayerMarker() {
        return playerMarker;
    }

    public int getSequenceCounter() {
        return sequenceCounter;
    }

    public void setSequenceCounter(int val) {
        sequenceCounter = val;
    }

    public List<Card> getCardsList() {
        return cardsList;
    }

    public List<Space> getSequenceList() {
        return sequenceList;
    }

    public void setSequenceList(List<Space> val) {
        sequenceList = val;
    }

    public Card selectCard(Card card) {
        if (cardsList.contains(card)) {
            cardsList.remove(card);
            return card;
        }
        return null;
    }

    public void takeMarker() {
        // TODO: take marker logic

    }

    public void selectSpace() {
        // TODO: select space logic

    }
}
