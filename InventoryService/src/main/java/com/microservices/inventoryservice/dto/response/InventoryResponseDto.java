package com.microservices.inventoryservice.dto.response;

import java.util.Date;

public record InventoryResponseDto(
        long id,
        long productId,
        long inventoryAmount,
        Date createDate,
        Date updatedDate) {
}
