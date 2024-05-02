package com.laba.mobilemarket.order.controller;

import com.laba.mobilemarket.order.dto.OrderRequestDto;
import com.laba.mobilemarket.order.dto.OrderResponseDto;
import com.laba.mobilemarket.order.feign.OrderFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFeignClient orderFeignClient;

    @PostMapping
    ResponseEntity<Void> create(@RequestBody OrderRequestDto orderRequestDto) {
        return orderFeignClient.save(orderRequestDto);
    }

    @GetMapping
    ResponseEntity<List<OrderResponseDto>> findAll() {
        return orderFeignClient.findAll();
    }

    @DeleteMapping
    void delete(@RequestParam long orderId) {
        orderFeignClient.delete(orderId);
    }

}
