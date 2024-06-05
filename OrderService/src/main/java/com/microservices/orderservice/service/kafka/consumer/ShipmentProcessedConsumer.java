package com.microservices.orderservice.service.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.orderservice.enums.OrderStatus;
import com.microservices.orderservice.event.shipmentprocessed.ShipmentProcessedEvent;
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
public class ShipmentProcessedConsumer {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    @KafkaListener(topics = "shipmentProcessedTopic", groupId = "shipmentProcessed")
    @RetryableTopic(attempts = "1", dltStrategy = DltStrategy.FAIL_ON_ERROR)
    public void listenShipmentProcessed(String message) {
        ShipmentProcessedEvent shipmentProcessed;

        try {
            shipmentProcessed = objectMapper.readValue(message, ShipmentProcessedEvent.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        orderService.setOrderStatusByOrderId(shipmentProcessed.orderId(), OrderStatus.COMPLETED);
    }
}
