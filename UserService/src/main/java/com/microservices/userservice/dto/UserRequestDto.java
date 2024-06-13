package com.microservices.userservice.dto;

public record UserRequestDto(String firstname, String lastname, String email, String password, String phoneNumber,
                             String address, String role, boolean premium) {
}