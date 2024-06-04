package com.microservices.shipmentservice.event.stockupdated;

public record InventoryProduct( long productId,
         long initialStockAmount,
         long finalStockAmount)  {

}
