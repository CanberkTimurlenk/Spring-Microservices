package com.robotdreams.orderservice.dto.response.external;

import lombok.Builder;

import java.util.Date;

@Builder
public record ProductResponseDto(
        Date createDate,
        Date updateDate,
        String serverTime,
        long id,
        String name,
        String category,
        String photoUrl,
        String description,
        double price,
        double weight

) {
}
