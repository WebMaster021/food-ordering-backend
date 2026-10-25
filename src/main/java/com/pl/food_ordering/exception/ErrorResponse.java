package com.pl.food_ordering.exception;

public class ErrorResponse {
    private String message;

    public ErrorResponse () {};

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
