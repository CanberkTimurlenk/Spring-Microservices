package com.robotdreams.orderservice.dto.response.external;

import java.util.Date;

public record UserInfoResponseDto(long id, Date createDate, Date updateDate, String serverTime,
                                  String firstname, String lastname,
                                  String email, String phoneNumber, String address, boolean premium
) {
}
