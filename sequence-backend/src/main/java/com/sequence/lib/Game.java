package com.sequence.lib;

import java.util.Random;
import static com.sequence.Constants.*;

public class Game {
    private Board board;
    private Player red;
    private Player blue;
    private Deck deck;
    private boolean redsTurn;

    public Game() {
        redsTurn = new Random().nextBoolean();
        board = new Board();
        deck = new Deck();
    }

    public void startGame() {
        board.initBoard();  // Initialize board
        deck.initDeck();    // Initialize deck
        deck.shuffle();     // Shuffle deck
        red = new Player(board, deck, PLAYER_RED);      // Initialize red player
        blue = new Player(board, deck, PLAYER_BLUE);    // Initialize blue player

        // Get 7 cards for red player from deck
        for (int i = 0; i < 7; i++) {
            red.addCard(deck.deal());
        }

        // Get 7 cards for blue player from deck
        for (int i = 0; i < 7; i++) {
            blue.addCard(deck.deal());
        }
    }

    public void selectCard(Card card) {
        Player currentPlayer = redsTurn ? red : blue;   // Get player
        currentPlayer.selectCard(card);     // Select card and remove from player's hand
        currentPlayer.addCard(deck.deal()); // Add new card to player's hand from deck
    }

    public int selectSpace(int cardSuitNum, int x, int y) {
        Player currentPlayer = redsTurn ? red : blue;   // Get player
        Space chosenSpace = currentPlayer.selectSpace(cardSuitNum, x, y); // Get space chosen by player

        // Check if there is a win on the board, after player makes move on board
        int win = board.checkSequence(chosenSpace.getxLocation(), chosenSpace.getyLocation(), currentPlayer);
        if (win == 2) {
            // If a player wins, return the player's marker integer (1 or -1 for red or blue)
            return currentPlayer.getPlayerMarker();
        }
        nextTurn();
        return 0; // Return 0 if there is no winner yet
    }

    public Board getBoard() {
        return board;
    }

    public Player getRed() {
        return red;
    }

    public Player getBlue() {
        return blue;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setRed(Player red) {
        this.red = red;
    }

    public void setBlue(Player blue) {
        this.blue = blue;
    }

    public boolean isRedsTurn() {
        return redsTurn;
    }

    public int getCurrentPlayerNum() {
        return redsTurn ? 1 : -1;
    }

    public Player getCurrentPlayer() {
        return redsTurn ? red : blue;
    }

    public Player getOtherPlayer() {
        return redsTurn ? blue : red;
    }

    public void nextTurn() {redsTurn = !redsTurn;}
}
