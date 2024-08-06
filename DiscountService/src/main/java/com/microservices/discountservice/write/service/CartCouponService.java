package com.microservices.discountservice.write.service;


import com.microservices.discountservice.dto.CartCouponRequestDto;
import com.microservices.discountservice.dto.CartCouponResponseDto;
import com.microservices.discountservice.entity.CartCoupon;
import com.microservices.discountservice.write.repository.CartCouponRepository;
import com.microservices.discountservice.write.service.mapper.CartCouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartCouponService {

    private final CartCouponRepository couponRepository;
    private final CartCouponMapper couponMapper;

    public Long save(CartCouponRequestDto couponRequestDto) {

        CartCoupon coupon = couponMapper.cartCouponRequestDtoToCartCoupon(couponRequestDto);

        return couponRepository.save(coupon).getId();
    }

    public boolean deleteByID(long couponId) {

        if (!couponRepository.existsById(couponId))
            return false;

        couponRepository.deleteById(couponId);
        return true;
    }

    public Optional<CartCouponResponseDto> update(long couponId, CartCouponRequestDto couponRequestDto) {

        return couponRepository.findByIdAndExpirationDateAfter(couponId, new Date())
                .map(coupon -> {
                    couponMapper.updateCartCoupon(coupon, couponRequestDto);
                    couponRepository.save(coupon);
                    return couponMapper.cartCouponToCartCouponResponseDto(coupon);
                });
    }

    public Optional<CartCouponResponseDto> findById(long couponId) {

        Optional<CartCoupon> coupon = couponRepository.findByIdAndExpirationDateAfter(couponId, new Date());
        return coupon.map(couponMapper::cartCouponToCartCouponResponseDto);
    }

    public List<CartCouponResponseDto> findAll() {

        return couponRepository.findAllByExpirationDateAfter(new Date()).stream()
                .map(couponMapper::cartCouponToCartCouponResponseDto).toList();
    }
}