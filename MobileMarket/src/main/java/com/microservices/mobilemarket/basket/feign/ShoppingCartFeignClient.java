package com.microservices.mobilemarket.basket.feign;

import com.microservices.mobilemarket.basket.dto.ShoppingCartResponseDto;
import com.microservices.mobilemarket.basket.dto.ShoppingCartRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "BasketService")
public interface ShoppingCartFeignClient {

    @GetMapping
    public ResponseEntity<List<ShoppingCartResponseDto>> findAll();

    @GetMapping("/{userId}")
    public ResponseEntity<ShoppingCartResponseDto> findById(@PathVariable long userId);

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable long userId);

    @PostMapping
    public ResponseEntity<Void> save(ShoppingCartRequestDto shoppingCartRequestDto);

    @PutMapping("/{userId}")
    public ResponseEntity<Void> update(@PathVariable long userId, ShoppingCartRequestDto shoppingCartRequestDto);
}