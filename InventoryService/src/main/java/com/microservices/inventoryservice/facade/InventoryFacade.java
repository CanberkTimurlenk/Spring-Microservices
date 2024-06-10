package com.microservices.inventoryservice.facade;

import com.microservices.inventoryservice.dto.request.StockDecrementDto;
import com.microservices.inventoryservice.event.ordercreated.OrderCreatedEvent;
import com.microservices.inventoryservice.event.stockupdated.InventoryProduct;
import com.microservices.inventoryservice.event.stockupdated.StockUpdatedEvent;
import com.microservices.inventoryservice.event.stockupdated.stockupdatecancelled.StockUpdateCancelledEvent;
import com.microservices.inventoryservice.exceptionhandling.InsufficientStockAmountException;
import com.microservices.inventoryservice.service.InventoryService;
import com.microservices.inventoryservice.service.kafka.producer.InventoryProducer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InventoryFacade {

    private static final Logger logger = LoggerFactory.getLogger(InventoryFacade.class);

    private final InventoryProducer inventoryProducer;
    private final InventoryService inventoryService;

    public void decreaseStock(List<StockDecrementDto> decrementDto, OrderCreatedEvent order) {

        List<InventoryProduct> resultList = null;

        try {
            resultList = inventoryService.stockDecrement(decrementDto);
            inventoryProducer.sendStockUpdatedEventToKafka(new StockUpdatedEvent(order.orderId(), resultList, new Date()));
        } catch (InsufficientStockAmountException e) {
            // Execute if the stock amount is less than 0 after deduction
            logger.error("{} : occured while executing order saga", e.getMessage());
            inventoryProducer.sendStockUpdateCancelledEventToKafka(new StockUpdateCancelledEvent(order.orderId(), resultList, new Date()));
        }
    }
}
