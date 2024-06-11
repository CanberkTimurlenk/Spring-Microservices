package com.microservices.inventoryservice.service.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.inventoryservice.dto.request.StockDecrementDto;
import com.microservices.inventoryservice.event.ordercreated.OrderCreatedEvent;
import com.microservices.inventoryservice.facade.InventoryFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCreatedConsumer {

    private final ObjectMapper objectMapper;
    private final InventoryFacade inventoryFacade;

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

        inventoryFacade.decreaseStock(decrementDto, order.orderId());
    }
}
