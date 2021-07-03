package com.sequence.res;

public class PingResponse implements Response {
    private String message;

    public PingResponse(String message) {
        this.message = message;
    }

    public PingResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
