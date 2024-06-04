package com.microservices.inventoryservice.service.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.inventoryservice.dto.request.StockDecrementDto;
import com.microservices.inventoryservice.event.ordercreated.OrderCreatedEvent;
import com.microservices.inventoryservice.event.stockupdated.StockUpdatedEvent;
import com.microservices.inventoryservice.service.InventoryService;
import com.microservices.inventoryservice.service.kafka.producer.InventoryProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCreatedConsumer {

    private final ObjectMapper objectMapper;
    private final InventoryService inventoryService;
    private final InventoryProducer inventoryProducer;

    @KafkaListener(topics = "orderCreatedTopic", groupId = "orderCreated")
    @RetryableTopic(attempts = "1", dltStrategy = DltStrategy.FAIL_ON_ERROR)
    public void listenOrderCreated(String message) {

        OrderCreatedEvent order;

        try {
            order = objectMapper.readValue(message, OrderCreatedEvent.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<StockDecrementDto> decrementDto =
                order.products().stream()
                        .map(p -> new StockDecrementDto(p.productId(), p.quantity()))
                        .toList();

        var resultSet = inventoryService.stockDecrement(decrementDto);
        inventoryProducer.sendStockUpdatedEventToKafka(new StockUpdatedEvent(order.orderId(), resultSet, new Date()));
    }
}
