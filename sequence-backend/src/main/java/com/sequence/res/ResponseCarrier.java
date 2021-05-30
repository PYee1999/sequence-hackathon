package com.sequence.res;

public class ResponseCarrier {
    private String type;
    private Response body;

    public ResponseCarrier() {}

    public ResponseCarrier(String type, Response body) {
        this.type = type;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Response getBody() {
        return body;
    }

    public void setBody(Response body) {
        this.body = body;
    }
}
