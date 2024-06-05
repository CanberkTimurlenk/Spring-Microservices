package com.microservices.orderservice.event.shipmentprocessed;

public record ProcessedProductShipment(long id, long productId, int quantity) {
}
