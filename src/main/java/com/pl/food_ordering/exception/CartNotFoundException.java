package com.pl.food_ordering.exception;

public class CartNotFoundException extends ResourceNotFoundException{
    public CartNotFoundException (String message) {
        super(message);
    }
}
