package com.microservices.shipmentservice.event.shipmentcancelled;

public record ShipmentCancelledEvent(long shipmentId, long orderId) {
}