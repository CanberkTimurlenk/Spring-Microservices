package com.microservices.inventoryservice.facade;

import com.microservices.inventoryservice.dto.request.StockDecrementDto;
import com.microservices.inventoryservice.event.stockupdated.InventoryProduct;
import com.microservices.inventoryservice.event.stockupdated.StockUpdatedEvent;
import com.microservices.inventoryservice.event.stockupdated.stockupdatecancelled.StockUpdateCancelledEvent;
import com.microservices.inventoryservice.exceptionhandling.InventoryException;
import com.microservices.inventoryservice.service.InventoryService;
import com.microservices.inventoryservice.service.kafka.producer.InventoryProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.microservices.inventoryservice.dto.request.StockIncrementDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class InventoryFacade {

    private final InventoryProducer inventoryProducer;
    private final InventoryService inventoryService;

    public void decreaseStock(List<StockDecrementDto> decrementDtoList, long orderId) {

        try {
            List<InventoryProduct> resultList = inventoryService.reserveStock(decrementDtoList);
            inventoryProducer.sendStockUpdatedEventToKafka(new StockUpdatedEvent(orderId, resultList, new Date()));
        } catch (InventoryException e) {
            // Execute if an exception occurs during deduction
            log.error("{} : occurred while executing order saga", e.getMessage());
            increaseStock(decrementDtoList.stream().map(d ->
                            new StockIncrementDto(d.productId(), d.quantity())).toList(), orderId);

        }
    }

    public void increaseStock(List<StockIncrementDto> incrementDtoList, long orderId) {
        List<InventoryProduct> resultList = inventoryService.increaseStock(incrementDtoList);
        inventoryProducer.sendStockUpdateCancelledEventToKafka(new StockUpdateCancelledEvent(orderId, resultList, new Date()));
    }
}
