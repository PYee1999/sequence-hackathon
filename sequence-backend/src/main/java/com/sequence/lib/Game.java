package com.sequence;

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
        board.initBoard();
        deck.initDeck();
        deck.shuffle();
        red = new Player(board, deck, PLAYER_RED);
        blue = new Player(board, deck, PLAYER_BLUE);

        for (int i = 0; i < 7; i++) {
            red.addCard(deck.deal());
        }
        for (int i = 0; i < 7; i++) {
            blue.addCard(deck.deal());
        }
    }

    public void nextTurn(Card card) {
        Player currentPlayer = redsTurn ? red : blue;
        currentPlayer.selectCard(card);
        currentPlayer.takeMarker();
        Space chosenSpace = currentPlayer.selectSpace(); // TODO: Need to check that selectSpace() returns Space
        currentPlayer.addCard(deck.deal());
        int win = board.checkSequence(chosenSpace.getxLocation(), chosenSpace.getyLocation(), currentPlayer);
        if (win == 2) {
            // TODO: Declare winner
        }
        redsTurn = !redsTurn;
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
}
