package com.microservices.orderservice.service.kafka.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.orderservice.dto.request.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.order.created}")
    private static String orderCreatedTopic;

    public void sendMessageKafkaOrderCreated(OrderRequestDto productUpdateRequestDto) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            String productSaveRequestDtoStr = null;
//            try {
//                //productSaveRequestDtoStr = objectMapper.writeValueAsString(productDto);
//            } //catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//
//        } finally {

    }


}