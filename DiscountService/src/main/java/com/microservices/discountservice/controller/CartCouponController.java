package com.microservices.discountservice.controller;

import com.microservices.discountservice.dto.CartCouponRequestDto;
import com.microservices.discountservice.dto.CartCouponResponseDto;
import com.microservices.discountservice.service.CartCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart-coupons")
public class CartCouponController {

    private final CartCouponService cartCouponService;

    @GetMapping
    public ResponseEntity<List<CartCouponResponseDto>> findAll() {
        return ResponseEntity.ok(cartCouponService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartCouponResponseDto> findById(@PathVariable long id) {

        Optional<CartCouponResponseDto> coupon = cartCouponService.findById(id);

        return coupon.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {

        return cartCouponService.deleteByID(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CartCouponRequestDto couponRequestDto)
            throws URISyntaxException {

        long id = cartCouponService.save(couponRequestDto);

        return ResponseEntity.created(new URI("/coupons" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody CartCouponRequestDto couponRequestDto) {

        Optional<CartCouponResponseDto> coupon =
                cartCouponService.update(id, couponRequestDto);

        return coupon.isPresent()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}