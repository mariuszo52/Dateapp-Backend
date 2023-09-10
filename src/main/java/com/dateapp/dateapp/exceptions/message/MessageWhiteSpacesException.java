package com.dateapp.dateapp.exceptions.message;

public class MessageWhiteSpacesException extends MessageException {
    private static final String MESSAGE = "Message cannot contains only whitespaces.";

    public MessageWhiteSpacesException(String message) {
        super(message);
    }

    public MessageWhiteSpacesException() {
        super(MESSAGE);
    }
}
