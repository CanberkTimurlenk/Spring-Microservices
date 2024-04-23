package com.robotdreams.pricingservice.dto.user;


import java.util.Date;

public record UserResponseDto(long id, String serverTime,
                              String firstname, String lastname,
                              String email, String phoneNumber, String address, boolean premium) {

}

