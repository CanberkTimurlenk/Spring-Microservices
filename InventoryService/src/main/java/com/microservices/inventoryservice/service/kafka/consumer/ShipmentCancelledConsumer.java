package com.microservices.inventoryservice.service.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.inventoryservice.dto.request.StockDecrementDto;
import com.microservices.inventoryservice.dto.request.StockIncrementDto;
import com.microservices.inventoryservice.event.shipmentcancelled.ShipmentCancelledEvent;
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
public class ShipmentCancelledConsumer {
    private final ObjectMapper objectMapper;
    private final InventoryFacade inventoryFacade;

    @KafkaListener(topics = "shipmentCancelledTopic", groupId = "shipmentCancelled")
    @RetryableTopic(attempts = "1", dltStrategy = DltStrategy.FAIL_ON_ERROR)
    public void listenShipmentCancelledEvent(String message) {

        ShipmentCancelledEvent shipmentCancelled;

        try {
            shipmentCancelled = objectMapper.readValue(message, ShipmentCancelledEvent.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<StockIncrementDto> incrementDto =
                shipmentCancelled.productShipments().stream()
                        .map(p -> new StockIncrementDto(p.productId(), p.quantity()))
                        .toList();

        inventoryFacade.increaseStock(incrementDto, shipmentCancelled.orderId());
    }
}
