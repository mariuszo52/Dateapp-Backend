package com.dateapp.dateapp.exceptions.message;

public class EmptyMessageException extends MessageException {
    private static final String MESSAGE = "Message cannot be empty.";

    public EmptyMessageException() {
        super(MESSAGE);
    }

    public EmptyMessageException(String message) {
        super(message);
    }
}
