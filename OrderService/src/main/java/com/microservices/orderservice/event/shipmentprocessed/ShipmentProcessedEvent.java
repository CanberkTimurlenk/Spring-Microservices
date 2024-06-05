package com.microservices.orderservice.event.shipmentprocessed;

import java.util.List;

public record ShipmentProcessedEvent(long shipmentId, long orderId,
                                     List<ProcessedProductShipment> processedProductShipments) {
}
