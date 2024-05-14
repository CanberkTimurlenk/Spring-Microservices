package com.robotdreams.discountservice.controller;

import com.robotdreams.discountservice.dto.CouponRequestDto;
import com.robotdreams.discountservice.dto.CouponResponseDto;
import com.robotdreams.discountservice.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService couponService;

    @GetMapping
    public ResponseEntity<List<CouponResponseDto>> findAll() {
        return ResponseEntity.ok(couponService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponseDto> findById(@PathVariable long id) {

        Optional<CouponResponseDto> coupon = couponService.findById(id);

        return coupon.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {

        return couponService.deleteByID(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CouponRequestDto couponRequestDto)
            throws URISyntaxException {

        long id = couponService.save(couponRequestDto);

        return ResponseEntity.created(new URI("/coupons" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id,CouponRequestDto couponRequestDto)
    {

        Optional<CouponResponseDto> coupon =
                couponService.update(id,couponRequestDto);

        return coupon.isPresent()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }
}
