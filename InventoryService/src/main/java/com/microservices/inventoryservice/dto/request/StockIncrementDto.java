package com.microservices.inventoryservice.dto.request;

public record StockIncrementDto(long productId, long quantity) {
}
