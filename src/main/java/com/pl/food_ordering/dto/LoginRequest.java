package com.pl.food_ordering.dto;

public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest(){}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
