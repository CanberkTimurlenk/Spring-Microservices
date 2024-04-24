package com.robotdreams.userservice.dto;

import java.io.Serializable;


public record UserRequestDto(String firstname, String lastname, String email, String phoneNumber,
                             String address, boolean premium) implements Serializable {
}