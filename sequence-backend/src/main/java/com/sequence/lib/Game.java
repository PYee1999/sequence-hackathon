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

    // Initialize the game
    public void startGame() {
        board.initBoard();  // Initialize board
        deck.initDeck(board);    // Initialize deck
        deck.shuffle();     // Shuffle deck

        // Get 7 cards for red player from deck
        for (int i = 0; i < 7; i++) {
            red.addCard(deck.deal());
        }

        // Get 7 cards for blue player from deck
        for (int i = 0; i < 7; i++) {
            blue.addCard(deck.deal());
        }
    }

    // For a given player, select a card that player wants to use
    public void selectCard(Card card) {
        Player currentPlayer = redsTurn ? red : blue;   // Get player
        currentPlayer.selectCard(card);     // Select card and remove from player's hand
        currentPlayer.addCard(deck.deal()); // Add new card to player's hand from deck
    }

    // Selects space on the board that the player wants to occupy
    public int selectSpace(int cardSuitNum, int x, int y) {
        Player currentPlayer = redsTurn ? red : blue;   // Get player
        Space chosenSpace = currentPlayer.selectSpace(cardSuitNum, x, y); // Get space chosen by player

        // Check if there is a win on the board, after player makes move on board
        int seqCount = board.checkSequence(chosenSpace.getxLocation(), chosenSpace.getyLocation(), currentPlayer);
        if (seqCount == 2) {
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

    public void nextTurn() {
        redsTurn = !redsTurn;
        String playerColor = "";
        if (redsTurn == true) {
            playerColor = "RED";
        } else {
            playerColor = "BLUE";
        }
        System.out.println("It is now " + playerColor + " turn");
    }
}
