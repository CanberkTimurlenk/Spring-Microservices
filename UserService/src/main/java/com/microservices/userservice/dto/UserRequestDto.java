package com.microservices.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.UUID;


public record UserRequestDto(String firstname, String lastname, String email,String password, String phoneNumber,
                             String address, boolean premium, String role, @JsonIgnore String userImageKey) implements Serializable {

    public UserRequestDto {
        userImageKey = UUID.randomUUID().toString();
    }
}