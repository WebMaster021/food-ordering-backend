package com.pl.food_ordering.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    private String name;

    private BigDecimal price;


    public Food() {}

    public Long getFoodId() {return this.foodId;}

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public BigDecimal getPrice() {return this.price;}
    public void setPrice(BigDecimal price) {this.price = price;}
}
