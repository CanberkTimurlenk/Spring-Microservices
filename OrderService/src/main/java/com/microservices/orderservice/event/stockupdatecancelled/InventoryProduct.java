package com.microservices.orderservice.event.stockupdatecancelled;

public record InventoryProduct( long productId,
         long initialStockAmount,
         long finalStockAmount)  {

}
