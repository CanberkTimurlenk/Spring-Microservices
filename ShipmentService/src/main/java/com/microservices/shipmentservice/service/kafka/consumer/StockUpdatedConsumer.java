package com.microservices.shipmentservice.service.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.shipmentservice.dto.request.ProductShipmentRequestDto;
import com.microservices.shipmentservice.dto.request.ShipmentRequestDto;
import com.microservices.shipmentservice.event.stockupdated.StockUpdatedEvent;
import com.microservices.shipmentservice.service.ShipmentService;
import com.microservices.shipmentservice.service.kafka.producer.ShipmentProducer;
import com.microservices.shipmentservice.service.mapper.ShipmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StockUpdatedConsumer {

    private final ObjectMapper objectMapper;
    private final ShipmentService shipmentService;
    private final ShipmentProducer shipmentProducer;
    private final ShipmentMapper shipmentMapper;

    @KafkaListener(topics = "stockUpdatedTopic", groupId = "stockUpdated")
    @RetryableTopic(attempts = "1", dltStrategy = DltStrategy.FAIL_ON_ERROR)
    public void listenStockUpdated(String message) {

        StockUpdatedEvent stockUpdatedEvent = null;

        try {
            stockUpdatedEvent = objectMapper.readValue(message, StockUpdatedEvent.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // map event with the service method parameter
        var productShipments = stockUpdatedEvent.products()
                .stream()
                .map(su ->
                        new ProductShipmentRequestDto(su.productId()
                                , su.initialStockAmount() - su.finalStockAmount())
                ).toList();

        var shipment = shipmentService.process(new ShipmentRequestDto(productShipments));
        shipmentProducer.sendShipmentProcessedEventToKafka(shipmentMapper.toShipmentProcessedEvent(shipment, stockUpdatedEvent.orderId()));
    }
}
