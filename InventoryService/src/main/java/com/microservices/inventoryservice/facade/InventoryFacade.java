package com.microservices.inventoryservice.facade;

import com.microservices.inventoryservice.dto.request.StockDecrementDto;
import com.microservices.inventoryservice.event.ordercreated.OrderCreatedEvent;
import com.microservices.inventoryservice.event.stockupdated.InventoryProduct;
import com.microservices.inventoryservice.event.stockupdated.StockUpdatedEvent;
import com.microservices.inventoryservice.event.stockupdated.stockupdatecancelled.StockUpdateCancelledEvent;
import com.microservices.inventoryservice.exceptionhandling.InventoryException;
import com.microservices.inventoryservice.service.InventoryService;
import com.microservices.inventoryservice.service.kafka.producer.InventoryProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class InventoryFacade {

    private static final Logger logger = LoggerFactory.getLogger(InventoryFacade.class);

    private final InventoryProducer inventoryProducer;
    private final InventoryService inventoryService;

    public void decreaseStock(List<StockDecrementDto> decrementDto, long orderId) {

        try {
            List<InventoryProduct> resultList = inventoryService.stockDecrement(decrementDto);
            inventoryProducer.sendStockUpdatedEventToKafka(new StockUpdatedEvent(orderId, resultList, new Date()));
        } catch (InventoryException e) {
            // Execute if an exception occurs during deduction
            logger.error("{} : occurred while executing order saga", e.getMessage());
            inventoryProducer.sendStockUpdateCancelledEventToKafka(new StockUpdateCancelledEvent(orderId, decrementDto, new Date()));
        }
    }
}
