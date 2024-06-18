package com.microservices.inventoryservice.event.shipmentcancelled;


public record ProductShipmentRequestDto(long productId, long quantity) {
}
