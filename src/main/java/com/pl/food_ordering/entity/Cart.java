package com.pl.food_ordering.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public Cart() {}

    public Long getCartId() {
        return cartId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
