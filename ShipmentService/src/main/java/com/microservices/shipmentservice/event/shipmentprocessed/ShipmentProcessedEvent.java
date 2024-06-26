package com.microservices.shipmentservice.event.shipmentprocessed;

import java.util.Date;
import java.util.List;

public record ShipmentProcessedEvent(long shipmentId, long orderId,
                                     List<ProcessedProductShipment> processedProductShipments, Date createdDate) {
}
