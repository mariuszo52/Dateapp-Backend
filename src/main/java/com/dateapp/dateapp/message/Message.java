package com.dateapp.dateapp.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    private String from;
    private String text;

    @JsonCreator
    public Message(@JsonProperty("from") String from, @JsonProperty("text") String text) {
        this.text = text;
        this.from = from;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
