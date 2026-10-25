package com.pl.food_ordering.dto;

public class RegisterResponse {
    private String email;
    private Long id;

    public RegisterResponse(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
