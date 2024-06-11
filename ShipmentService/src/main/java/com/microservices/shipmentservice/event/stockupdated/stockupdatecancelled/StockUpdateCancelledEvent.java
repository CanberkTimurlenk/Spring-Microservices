package com.microservices.shipmentservice.event.stockupdated.stockupdatecancelled;

import com.microservices.shipmentservice.event.stockupdated.InventoryProduct;

import java.util.Date;
import java.util.List;

public record StockUpdateCancelledEvent(long orderId, List<InventoryProduct> products, Date createdDate) {
}
