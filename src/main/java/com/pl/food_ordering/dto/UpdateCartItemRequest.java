package com.pl.food_ordering.dto;

public class UpdateCartItemRequest {
    private int quantity;

    public UpdateCartItemRequest () {}

    public void setQuantity(int quantity) {this.quantity = quantity;}
    public int getQuantity() {return quantity;}
}
