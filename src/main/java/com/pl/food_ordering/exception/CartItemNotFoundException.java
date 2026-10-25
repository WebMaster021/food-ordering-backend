package com.pl.food_ordering.exception;

public class CartItemNotFoundException extends ResourceNotFoundException{
    public CartItemNotFoundException(String message) {
        super(message);
    }
}
