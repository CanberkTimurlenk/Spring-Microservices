package com.microservices.mobilemarket.basket.controller;

import com.microservices.mobilemarket.basket.dto.ShoppingCartRequestDto;
import com.microservices.mobilemarket.basket.dto.ShoppingCartResponseDto;
import com.microservices.mobilemarket.basket.feign.ShoppingCartFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-carts")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartFeignClient shoppingCartFeignClient;

    @GetMapping
    public ResponseEntity<List<ShoppingCartResponseDto>> findAll() {
        return shoppingCartFeignClient.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ShoppingCartResponseDto> findById(@PathVariable long userId) {
        return shoppingCartFeignClient.findById(userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable long userId) {
        return shoppingCartFeignClient.deleteById(userId);
    }

    @PostMapping
    public ResponseEntity<Void> save(ShoppingCartRequestDto shoppingCartRequestDto) {
        return shoppingCartFeignClient.save(shoppingCartRequestDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> update(@PathVariable long userId, ShoppingCartRequestDto shoppingCartRequestDto) {
        return shoppingCartFeignClient.update(userId, shoppingCartRequestDto);
    }
}
