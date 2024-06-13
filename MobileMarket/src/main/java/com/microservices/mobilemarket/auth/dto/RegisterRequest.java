package com.microservices.mobilemarket.auth.dto;

public record RegisterRequest(String firstname,
                              String lastname,
                              String email,
                              String password,
                              String phoneNumber,
                              String address,
                              String role,
                              boolean premium) {


    public RegisterRequest copyWithNewEmail(String password) {
        return new RegisterRequest(
                this.firstname(),
                this.lastname(),
                this.email(),
                password,
                this.phoneNumber(),
                this.address(),
                this.role(),
                this.premium()
        );
    }
}
