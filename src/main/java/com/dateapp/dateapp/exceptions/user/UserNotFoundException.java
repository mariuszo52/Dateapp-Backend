package com.dateapp.dateapp.exceptions.user;

public class UserNotFoundException extends RuntimeException {
    private static final String MESSAGE = "User not found.";

    public UserNotFoundException() {
        super(MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
