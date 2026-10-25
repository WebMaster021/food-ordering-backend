package com.pl.food_ordering.service;

import com.pl.food_ordering.dto.LoginRequest;
import com.pl.food_ordering.dto.LoginResponse;
import com.pl.food_ordering.dto.RegisterRequest;
import com.pl.food_ordering.dto.RegisterResponse;
import com.pl.food_ordering.entity.User;
import com.pl.food_ordering.exception.DuplicateFoundException;
import com.pl.food_ordering.exception.InvalidCredentialsException;
import com.pl.food_ordering.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();

        if (userRepository.findByEmail(email).isPresent()) {
            throw new DuplicateFoundException("user already exists");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setRole("USER");

        User savedUser = userRepository.save(newUser);

        RegisterResponse response = new RegisterResponse();
        response.setEmail(savedUser.getEmail());
        response.setId(savedUser.getId());
        return response;
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
        String encodedPassword = user.getPassword();
        String rawPassword = loginRequest.getPassword();

        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        String token = jwtService.generateToken(user.getEmail());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        return response;
    }
}
