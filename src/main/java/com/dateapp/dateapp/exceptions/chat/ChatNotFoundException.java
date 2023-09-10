package com.dateapp.dateapp.exceptions.chat;

public class ChatNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Chat not found.";

    public ChatNotFoundException() {
        super(MESSAGE);
    }

    public ChatNotFoundException(String message) {
        super(message);
    }
}
