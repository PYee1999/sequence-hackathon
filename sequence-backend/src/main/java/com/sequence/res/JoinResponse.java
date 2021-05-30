package com.sequence.res;

public class JoinResponse implements Response {
    private int player;

    public JoinResponse() {
    }

    public JoinResponse(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
