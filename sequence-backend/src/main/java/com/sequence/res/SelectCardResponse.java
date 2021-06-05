package com.sequence.res;

import com.sequence.lib.Space;

import java.util.List;

public class SelectCardResponse implements Response {
    private List<Space> spaces;

    public SelectCardResponse() {
    }

    public SelectCardResponse(List<Space> spaces) {
        this.spaces = spaces;
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<Space> spaces) {
        this.spaces = spaces;
    }
}
