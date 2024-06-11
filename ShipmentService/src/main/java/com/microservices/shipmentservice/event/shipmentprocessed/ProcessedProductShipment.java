package com.microservices.shipmentservice.event.shipmentprocessed;

public record ProcessedProductShipment(long shipmentId, long productId, int quantity) {
}
