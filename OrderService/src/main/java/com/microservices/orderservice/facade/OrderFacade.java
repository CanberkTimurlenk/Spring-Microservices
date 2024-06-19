package com.microservices.orderservice.facade;

import com.microservices.orderservice.dto.request.OrderRequestDto;
import com.microservices.orderservice.service.OrderService;
import com.microservices.orderservice.service.kafka.producer.OrderProducer;
import com.microservices.orderservice.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final OrderProducer orderProducer;
    private final OrderMapper orderMapper;

    public void createOrder(OrderRequestDto orderRequestDto) {
        var response = orderService.save(orderRequestDto);
        orderProducer.sendOrderCreatedEventToKafka(orderMapper.toOrderCreatedEvent(response));
    }
}
