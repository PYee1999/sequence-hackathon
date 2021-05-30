package com.example.lib;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int playerMarker;
    private int sequenceCounter;
    private List<Space> sequenceList;
    private List<Card> cardsList;
    private Board board;

    // 1 is red, -1 is blue
    public Player(Board board, int playerColor) {
        this.board = board;
        playerMarker = playerColor;
        sequenceCounter = 0;
        cardsList = new ArrayList<>();
    }

    public void drawCard(Card card) {
        cardsList.add(card);
    }
}
