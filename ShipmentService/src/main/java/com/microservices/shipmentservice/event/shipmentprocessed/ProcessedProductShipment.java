package com.microservices.shipmentservice.event.shipmentprocessed;

public record ProcessedProductShipment(long id, long productId, int quantity) {
}
