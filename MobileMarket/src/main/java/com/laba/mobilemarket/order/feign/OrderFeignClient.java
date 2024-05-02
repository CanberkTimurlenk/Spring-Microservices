package com.laba.mobilemarket.order.feign;

import com.laba.mobilemarket.order.dto.OrderRequestDto;
import com.laba.mobilemarket.order.dto.OrderResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "OrderService")
public interface OrderFeignClient {

    @PostMapping("/orders")
    ResponseEntity<Void> save(@RequestBody OrderRequestDto orderRequestDto);

    @GetMapping("/orders")
    ResponseEntity<List<OrderResponseDto>> findAll();

    @DeleteMapping("/orders")
    void delete(@RequestParam long orderId);
}
