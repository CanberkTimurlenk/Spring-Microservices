package com.microservices.userservice.dto;

public record UserResponseDto(long id,
                              String firstname, String lastname, String password,
                              String email, String phoneNumber, String address, String role, boolean premium) {

}

