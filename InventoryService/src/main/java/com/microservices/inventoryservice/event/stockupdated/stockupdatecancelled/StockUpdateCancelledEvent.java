package com.microservices.inventoryservice.event.stockupdated.stockupdatecancelled;

import com.microservices.inventoryservice.dto.request.StockDecrementDto;

import java.util.Date;
import java.util.List;

public record StockUpdateCancelledEvent(long orderId, List<StockDecrementDto> products, Date createdDate) {
}
