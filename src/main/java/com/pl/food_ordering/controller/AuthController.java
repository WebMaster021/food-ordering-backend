package com.pl.food_ordering.controller;

import com.pl.food_ordering.dto.LoginRequest;
import com.pl.food_ordering.dto.LoginResponse;
import com.pl.food_ordering.dto.RegisterRequest;
import com.pl.food_ordering.dto.RegisterResponse;
import com.pl.food_ordering.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/register")
    public RegisterResponse registerUser(@RequestBody RegisterRequest registerRequest) {
        return authService.registerUser(registerRequest);
    }

    @PostMapping("/auth/login")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) {
        return authService.loginUser(loginRequest);
    }
}
