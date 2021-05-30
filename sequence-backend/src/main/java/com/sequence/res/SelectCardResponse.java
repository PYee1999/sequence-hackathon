package com.sequence.res;

import com.sequence.lib.Space;

public class SelectCardResponse implements Response {
    private boolean multiple;
    private Space first, second;

    public SelectCardResponse() {
    }

    public SelectCardResponse(boolean multiple, Space first, Space second) {
        this.multiple = multiple;
        this.first = first;
        this.second = second;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public Space getFirst() {
        return first;
    }

    public void setFirst(Space first) {
        this.first = first;
    }

    public Space getSecond() {
        return second;
    }

    public void setSecond(Space second) {
        this.second = second;
    }
}
