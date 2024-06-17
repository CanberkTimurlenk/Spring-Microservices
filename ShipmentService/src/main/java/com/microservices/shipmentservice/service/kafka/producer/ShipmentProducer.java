package com.microservices.shipmentservice.service.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.shipmentservice.event.shipmentcancelled.ShipmentCancelledEvent;
import com.microservices.shipmentservice.event.shipmentprocessed.ShipmentProcessedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
public class ShipmentProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${topic.shipment.processed}")
    private String shipmentProcessed;

    @Value("${topic.shipment.cancelled}")
    private String shipmentCancelled;

    public void sendShipmentProcessedEventToKafka(ShipmentProcessedEvent shipmentProcessedEvent) {
        String valueAsString = null;

        try {
            valueAsString = objectMapper.writeValueAsString(shipmentProcessedEvent);

            CompletableFuture<SendResult<String, String>> sendResultCompletableFuture
                    = kafkaTemplate.send(shipmentProcessed, valueAsString);

            sendResultCompletableFuture.whenComplete((result, ex) -> {
                if (ex == null)
                    log.info("Sent shipment processed message = {} with offset = {}", shipmentProcessedEvent, result.getRecordMetadata().offset());
                else
                    log.error("Unable to send shipment processed message = {} due to: {}", shipmentProcessedEvent, ex.getMessage());
            });

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("Shipment processed message is not sent ", e);
        }
    }

    public void sendShipmentCancelledEventToKafka(ShipmentCancelledEvent shipmentCancelledEvent) {
        String valueAsString = null;

        try {
            valueAsString = objectMapper.writeValueAsString(shipmentCancelledEvent);

            CompletableFuture<SendResult<String, String>> sendResultCompletableFuture
                    = kafkaTemplate.send(shipmentCancelled, valueAsString);

            sendResultCompletableFuture.whenComplete((result, ex) -> {
                if (ex == null)
                    log.info("Sent shipment cancelled message = {} with offset = {}", shipmentCancelledEvent, result.getRecordMetadata().offset());
                else
                    log.error("Unable to send shipment cancelled message = {} due to: {}", shipmentCancelledEvent, ex.getMessage());

            });

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("Shipment cancelled message is not sent ", e);
        }

    }
}
