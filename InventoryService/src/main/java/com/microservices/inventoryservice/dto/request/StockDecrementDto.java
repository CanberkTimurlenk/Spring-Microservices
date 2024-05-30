package com.microservices.inventoryservice.dto.request;


public record StockDecrementDto(long productId, long quantity) {

}
