package com.robotdreams.discountservice.service;


import com.robotdreams.discountservice.dto.CartCouponRequestDto;
import com.robotdreams.discountservice.dto.CartCouponResponseDto;
import com.robotdreams.discountservice.entity.CartCoupon;
import com.robotdreams.discountservice.repository.CartCouponRepository;
import com.robotdreams.discountservice.service.mapper.CartCouponMapper;
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
    private final SequenceGeneratorService sequenceGenerator;

    public Long save(CartCouponRequestDto couponRequestDto) {

        CartCoupon coupon = null;
        coupon = couponMapper.cartCouponRequestDtoToCartCoupon(couponRequestDto);

        coupon.setId(sequenceGenerator.generateSequence(CartCoupon.SEQUENCE_NAME));
        return couponRepository.save(coupon).getId();
    }

    public boolean deleteByID(long couponId) {

        if (!couponRepository.existsById(couponId))
            return false;

        couponRepository.deleteById(couponId);
        return true;
    }

    public Optional<CartCouponResponseDto> update(long couponId, CartCouponRequestDto couponRequestDto) {

        var couponToUpdate = couponRepository.findByIdAndExpirationDateAfter(couponId, new Date());

        if (couponToUpdate.isPresent()) {
            CartCoupon coupon = couponToUpdate.get();
            couponMapper.updateCartCoupon(coupon, couponRequestDto);
            couponRepository.save(coupon);
            return Optional.of(couponMapper.cartCouponToCartCouponResponseDto(coupon));
        }
        return Optional.empty();
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