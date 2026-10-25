package com.pl.food_ordering.controller;

import com.pl.food_ordering.dto.AddCartItemRequest;
import com.pl.food_ordering.dto.CartItemResponse;
import com.pl.food_ordering.dto.UpdateCartItemRequest;
import com.pl.food_ordering.entity.Cart;
import com.pl.food_ordering.entity.CartItem;
import com.pl.food_ordering.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/carts")
    public Cart careateCart(Authentication authentication) {
        String email = authentication.getName();
        return cartService.createCart(email);
    }

    @PostMapping("/carts/{cartId}/items")
    public CartItemResponse addItemToCart(@PathVariable Long cartId, @RequestBody AddCartItemRequest request) {
        return cartService.addItemToCart(cartId, request);
    }

    @GetMapping("/carts/{cartId}")
    public Cart findCartById(@PathVariable Long cartId, Authentication authentication) {
        return cartService.findCartById(cartId, authentication.getName());
    }

    @GetMapping("/carts/{cartId}/items")
    public List<CartItemResponse> getCartItems(@PathVariable Long cartId) {
        return cartService.getCartItems(cartId);
    }

    @PatchMapping("carts/{cartId}/items/{foodId}")
    public ResponseEntity<Void> updateCartItemQuantity(@PathVariable Long cartId, @PathVariable Long foodId,
                                                 @RequestBody UpdateCartItemRequest request) {
        cartService.updateCartItemQuantity(cartId, foodId, request);
        return ResponseEntity.noContent().build();
    }
}
