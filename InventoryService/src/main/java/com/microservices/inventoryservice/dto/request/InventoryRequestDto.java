package com.microservices.inventoryservice.dto.request;

public record InventoryRequestDto(
        long productId,
        long inventoryAmount) {
}
