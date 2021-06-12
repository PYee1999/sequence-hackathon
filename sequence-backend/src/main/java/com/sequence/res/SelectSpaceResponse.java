package com.sequence.res;

import com.sequence.lib.Board;
import com.sequence.lib.Card;

import java.util.List;

public class SelectSpaceResponse implements Response {
    private int winner, currentPlayer;
    private Board board;
    private List<Card> hand;

    public SelectSpaceResponse() {
    }

    public SelectSpaceResponse(int winner, int currentPlayer, Board board, List<Card> hand) {
        this.winner = winner;
        this.currentPlayer = currentPlayer;
        this.board = board;
        this.hand = hand;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
}
