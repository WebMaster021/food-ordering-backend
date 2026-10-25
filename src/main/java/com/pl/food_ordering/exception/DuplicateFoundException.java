package com.pl.food_ordering.exception;

public class DuplicateFoundException extends RuntimeException {
    public DuplicateFoundException(String message) {
        super(message);
    }
}
