package com.dateapp.dateapp.exceptions.message;

public class MessageToLongException extends MessageException{
    private static final String MESSAGE = "Message is too long. Max size is 1000 chars";
    public MessageToLongException(){
        super(MESSAGE);
    }
    public MessageToLongException(String message) {
        super(message);
    }
}
