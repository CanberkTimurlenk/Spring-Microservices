package com.robotdreams.pricingservice.dto.user;


import java.util.Date;

public record UserResponseDto(long id, Date createDate, Date updateDate, String serverTime,
                              String firstname, String lastname,
                              String email, String phoneNumber, String address, boolean premium) {

}

