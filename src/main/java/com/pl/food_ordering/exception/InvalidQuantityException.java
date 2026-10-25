package com.pl.food_ordering.exception;

public class InvalidQuantityException extends RuntimeException{
    public InvalidQuantityException (String message) {
        super(message);
    }
}
