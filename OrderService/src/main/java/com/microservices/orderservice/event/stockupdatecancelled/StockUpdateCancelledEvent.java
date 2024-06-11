package com.microservices.orderservice.event.stockupdatecancelled;

import java.util.Date;
import java.util.List;

public record StockUpdateCancelledEvent(long orderId, List<InventoryProduct> products, Date createdDate) {
}
