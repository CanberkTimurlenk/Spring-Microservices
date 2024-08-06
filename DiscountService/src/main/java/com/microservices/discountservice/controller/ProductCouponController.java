package com.microservices.discountservice.controller;

import com.microservices.discountservice.dto.ProductCouponRequestDto;
import com.microservices.discountservice.dto.ProductCouponResponseDto;
import com.microservices.discountservice.write.service.ProductCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-coupons")
public class ProductCouponController {

    private final ProductCouponService productCouponService;

    @GetMapping
    public ResponseEntity<List<ProductCouponResponseDto>> findAll() {
        return ResponseEntity.ok(productCouponService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCouponResponseDto> findById(@PathVariable long id) {

        Optional<ProductCouponResponseDto> coupon = productCouponService.findById(id);

        return coupon.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {

        return productCouponService.deleteByID(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProductCouponRequestDto couponRequestDto)
            throws URISyntaxException {

        long id = productCouponService.save(couponRequestDto);

        return ResponseEntity.created(new URI("/coupons" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody ProductCouponRequestDto couponRequestDto) {

        Optional<ProductCouponResponseDto> coupon =
                productCouponService.update(id, couponRequestDto);

        return coupon.isPresent()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }
}