package com.pl.food_ordering.dto;

import jakarta.persistence.Id;

public class AddCartItemRequest {

    private Long foodId;

    private int quantity;

    public AddCartItemRequest () {}

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
