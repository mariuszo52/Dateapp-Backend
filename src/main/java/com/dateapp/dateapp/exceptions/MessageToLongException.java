package com.dateapp.dateapp.exceptions;

public class MessageToLongException extends RuntimeException{
    private static final String MESSAGE = "Message is too long. Max size is 1000 chars";
    public MessageToLongException(){
        super(MESSAGE);
    }
    public MessageToLongException(String message) {
        super(message);
    }
}
