package com.pl.food_ordering.exception;

public class FoodNotFoundException extends ResourceNotFoundException {
    public FoodNotFoundException(String message) {
        super(message);
    }
}
