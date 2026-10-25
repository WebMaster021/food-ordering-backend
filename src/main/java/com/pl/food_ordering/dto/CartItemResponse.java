package com.pl.food_ordering.dto;

import com.pl.food_ordering.entity.CartItem;

import java.math.BigDecimal;

public class CartItemResponse {

    private String name;

    private BigDecimal price;

    private int quantity;

    private Long foodId;

    public CartItemResponse() {}

    public Long getFoodId() {return foodId;}
    public void setFoodId(Long foodId) {this.foodId = foodId;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public BigDecimal getPrice() {return price;}
    public void setPrice(BigDecimal price) {this.price = price;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    public CartItemResponse(CartItem item) {
        this.foodId = item.getFood().getFoodId();
        this.name = item.getFood().getName();
        this.price = item.getFood().getPrice();
        this.quantity = item.getQuantity();
    }
}
