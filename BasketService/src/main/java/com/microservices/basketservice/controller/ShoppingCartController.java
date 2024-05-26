package com.microservices.basketservice.controller;

import com.microservices.basketservice.dto.ShoppingCartRequestDto;
import com.microservices.basketservice.dto.ShoppingCartResponseDto;
import com.microservices.basketservice.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public ResponseEntity<List<ShoppingCartResponseDto>> findAll() {

        return ResponseEntity.ok(shoppingCartService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ShoppingCartResponseDto> findById(@PathVariable long userId) {

        Optional<ShoppingCartResponseDto> responseDto = shoppingCartService.findById(userId);

        return responseDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable long userId) {

        return shoppingCartService.deleteById(userId)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ShoppingCartRequestDto shoppingCartRequestDto)
            throws URISyntaxException {

        long userId = shoppingCartService.save(shoppingCartRequestDto);

        return ResponseEntity.created(new URI("/shopping-carts/" + userId)).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> update(@PathVariable long userId, ShoppingCartRequestDto shoppingCartRequestDto) {

        Optional<ShoppingCartResponseDto> shoppingCartResponseDto =
                shoppingCartService.update(userId, shoppingCartRequestDto);

        return shoppingCartResponseDto.isPresent()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}