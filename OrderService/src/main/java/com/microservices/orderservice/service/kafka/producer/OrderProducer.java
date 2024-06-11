package com.microservices.orderservice.service.kafka.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.orderservice.event.ordercreated.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value(value = "${topic.order.created}")
    private String orderCreatedTopic;

    public void sendOrderCreatedEventToKafka(OrderCreatedEvent orderCreatedEvent) {

        String valueAsString = null;

        try {
            valueAsString = objectMapper.writeValueAsString(orderCreatedEvent);

            CompletableFuture<SendResult<String, String>> sendResultCompletableFuture
                    = kafkaTemplate.send(orderCreatedTopic, valueAsString);

            sendResultCompletableFuture.whenComplete((result, ex) -> {
                if (ex == null)
                    logger.info("Sent message = {} with offset = {}", orderCreatedEvent, result.getRecordMetadata().offset());
                else
                    logger.error("Unable to send message = {} due to: {}", orderCreatedEvent, ex.getMessage());
            });

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("Message is not sent ", e);
        }
    }
}