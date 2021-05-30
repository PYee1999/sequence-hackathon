package com.sequence.res;

import com.sequence.lib.Board;
import com.sequence.lib.Card;

import java.util.List;

public class StartGameResponse implements Response {
    private List<Card> hand;
    private Board board;

    public StartGameResponse(List<Card> hand, Board board) {
        this.hand = hand;
        this.board = board;
    }

    public StartGameResponse() {
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
