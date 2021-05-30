package com.example.lib;

import java.util.Random;

public class Game {
    private Board board;
    private Player red;
    private Player blue;
    private Deck deck;
    private boolean currentPlayer;

    public Game() {
        currentPlayer = new Random().nextBoolean();
        board = new Board();
        board.initBoard();
        deck = new Deck();
        deck.initDeck();
        deck.shuffle();
        red = new Player(board, 1);
        blue = new Player(board, -1);
        for(int i = 0; i < 7; i++) {
            red.drawCard(deck.deal());
        }
        for(int i = 0; i < 7; i++) {
            blue.drawCard(deck.deal());
        }
    }
}
