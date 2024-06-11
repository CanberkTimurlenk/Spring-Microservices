package com.microservices.orderservice.service.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.orderservice.enums.OrderStatus;
import com.microservices.orderservice.event.shipmentprocessed.ShipmentProcessedEvent;
import com.microservices.orderservice.event.stockupdatecancelled.StockUpdateCancelledEvent;
import com.microservices.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StockUpdateCancelledConsumer {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    @KafkaListener(topics = "stockUpdateCancelledTopic", groupId = "stockUpdateCancelled")
    @RetryableTopic(attempts = "1", dltStrategy = DltStrategy.FAIL_ON_ERROR)
    public void listenStockUpdateCancelled(String message) {
        StockUpdateCancelledEvent stockUpdateCancelledEvent;

        try {
            stockUpdateCancelledEvent = objectMapper.readValue(message, StockUpdateCancelledEvent.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        orderService.setOrderStatusByOrderId(stockUpdateCancelledEvent.orderId(), OrderStatus.CANCELLED);
    }
}
