package com.pl.food_ordering.dto;

public class RegisterRequest {

    private String email;

    private String password;

    public RegisterRequest(){}

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
