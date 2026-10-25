package com.pl.food_ordering.repository;

import com.pl.food_ordering.entity.Cart;
import com.pl.food_ordering.entity.CartItem;
import com.pl.food_ordering.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndFood(Cart cart, Food food);
    Optional<CartItem> findByCart_CartIdAndFood_FoodId(Long cartId, Long foodId);
}
