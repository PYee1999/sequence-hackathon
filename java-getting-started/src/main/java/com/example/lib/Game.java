package com.example.lib;

import java.util.Random;

public class Game {
    private Board board;
    private Player red;
    private Player blue;
    private Deck deck;
    private boolean redsTurn;

    public Game() {
        redsTurn = new Random().nextBoolean();
        board = new Board();
        board.initBoard();
        deck = new Deck();
        deck.initDeck();
        deck.shuffle();
        red = new Player(board, deck, 1);
        blue = new Player(board, deck, -1);
        for (int i = 0; i < 7; i++) {
            red.addCard(deck.deal());
        }
        for (int i = 0; i < 7; i++) {
            blue.addCard(deck.deal());
        }
    }

    public void nextTurn() {
        // TODO: Logic for one turn
        redsTurn = !redsTurn;
    }
}
