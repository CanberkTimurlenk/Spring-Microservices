package com.microservices.inventoryservice.service.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.inventoryservice.event.stockupdated.StockUpdatedEvent;
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
public class InventoryProducer {

    private static final Logger logger = LoggerFactory.getLogger(InventoryProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${topic.stock.updated}")
    private String stockUpdatedTopic;

    public void sendStockUpdatedEventToKafka(StockUpdatedEvent stockUpdatedEvent) {
        String valueAsString = null;

        try {
            valueAsString = objectMapper.writeValueAsString(stockUpdatedEvent);

            CompletableFuture<SendResult<String, String>> sendResultCompletableFuture
                    = kafkaTemplate.send(stockUpdatedTopic, valueAsString);

            sendResultCompletableFuture.whenComplete((result, ex) -> {
                if (ex == null)
                    logger.info("Sent message = {} with offset = {}", stockUpdatedEvent, result.getRecordMetadata().offset());
                else
                    logger.error("Unable to send message = {} due to: {}", stockUpdatedEvent, ex.getMessage());

            });

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("Message is not sent ", e);
        }


    }

}
