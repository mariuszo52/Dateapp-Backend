package com.dateapp.dateapp.exceptions;

public class UnauthorizedResourceAccessException extends RuntimeException {
    private static final String MESSAGE = "Unauthorized access to the resource";
    public UnauthorizedResourceAccessException(String message) {
        super(message);
    }
    public UnauthorizedResourceAccessException() {
        super(MESSAGE);
    }
}
