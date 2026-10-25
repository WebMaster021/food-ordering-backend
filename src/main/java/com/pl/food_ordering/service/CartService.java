package com.pl.food_ordering.service;

import com.pl.food_ordering.dto.AddCartItemRequest;
import com.pl.food_ordering.dto.CartItemResponse;
import com.pl.food_ordering.dto.UpdateCartItemRequest;
import com.pl.food_ordering.entity.Cart;
import com.pl.food_ordering.entity.CartItem;
import com.pl.food_ordering.entity.Food;
import com.pl.food_ordering.entity.User;
import com.pl.food_ordering.exception.CartItemNotFoundException;
import com.pl.food_ordering.exception.CartNotFoundException;
import com.pl.food_ordering.exception.FoodNotFoundException;
import com.pl.food_ordering.exception.InvalidQuantityException;
import com.pl.food_ordering.repository.CartItemRepository;
import com.pl.food_ordering.repository.CartRepository;
import com.pl.food_ordering.repository.FoodRepository;
import com.pl.food_ordering.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final FoodRepository foodRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public CartService (FoodRepository foodRepository, CartRepository cartRepository,
                        CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.foodRepository = foodRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    public Cart createCart(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        Cart cart = new Cart();
        cart.setUser(user);

        return cartRepository.save(cart);
    }

    public CartItemResponse addItemToCart(Long cartId, AddCartItemRequest request) {
        Food food = foodRepository.findById(request.getFoodId())
                .orElseThrow(() -> new FoodNotFoundException("Food not found"));
        Cart exsistingCart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));
        Optional<CartItem> existingItem = cartItemRepository.findByCartAndFood(exsistingCart, food);
        CartItem item;
        if (existingItem.isPresent()) {
            item = existingItem.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
        } else {
            item =  new CartItem();
            item.setQuantity(request.getQuantity());
            item.setFood(food);
            item.setCart(exsistingCart);
        }
        CartItem savedItem = cartItemRepository.save(item);
        CartItemResponse response = new CartItemResponse();

        response.setFoodId(savedItem.getFood().getFoodId());
        response.setName(savedItem.getFood().getName());
        response.setPrice(savedItem.getFood().getPrice());
        response.setQuantity(savedItem.getQuantity());

        return response;
    }

    public Cart findCartById(Long cartId, String email) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() ->
                        new CartNotFoundException("Cart not found"));

        if (!cart.getUser().getEmail().equals(email)) {
            throw new AccessDeniedException("You cannot access this cart");
        }

        return cart;
    }

    public List<CartItemResponse> getCartItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));
        List<CartItemResponse> responses = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            CartItemResponse response = new CartItemResponse();
            response.setFoodId(item.getFood().getFoodId());
            response.setName(item.getFood().getName());
            response.setPrice(item.getFood().getPrice());
            response.setQuantity(item.getQuantity());
            responses.add(response);
        }
        return responses;
    }
    
    public void updateCartItemQuantity(Long cartId, Long foodId, UpdateCartItemRequest request) {
        Optional<CartItem> item = cartItemRepository.findByCart_CartIdAndFood_FoodId(cartId, foodId);
        CartItem cartItem = item.orElseThrow(() -> new CartItemNotFoundException("Cart item not found"));
        int quantity = request.getQuantity();
        if (quantity < 0) {
            throw new InvalidQuantityException("Quantity needs to be either zero or positive");
        } else if (quantity == 0) {
            cartItemRepository.delete(cartItem);
        } else {
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
    }
}
