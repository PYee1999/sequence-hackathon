package com.sequence.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestCarrier {
    @JsonProperty("type")
    private String type;

    @JsonProperty("body")
    private String body;

    public String getRequestType() {
        return type;
    }

    public String getBody() {
        return body;
    }
}
