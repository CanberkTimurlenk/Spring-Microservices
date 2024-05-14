package com.robotdreams.discountservice.service;


import com.robotdreams.discountservice.dto.CouponRequestDto;
import com.robotdreams.discountservice.dto.CouponResponseDto;
import com.robotdreams.discountservice.entity.Coupon;
import com.robotdreams.discountservice.repository.CouponRepository;
import com.robotdreams.discountservice.service.mapper.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;
    private final SequenceGeneratorService sequenceGenerator;



    public Long save(CouponRequestDto couponRequestDto) {

        Coupon coupon = couponMapper.couponRequestDtoToCoupon(couponRequestDto);
        coupon.setId(sequenceGenerator.generateSequence(Coupon.SEQUENCE_NAME));
        return couponRepository.save(coupon).getId();
    }

    public boolean deleteByID(long couponId) {

        if (!couponRepository.existsById(couponId))
            return false;

        couponRepository.deleteById(couponId);
        return true;
    }

    public Optional<CouponResponseDto> update(long couponId, CouponRequestDto couponRequestDto) {

        // such a coupon does not exist
        if (!couponRepository.existsById(couponId))
            return Optional.empty();

        Coupon coupon = couponMapper.updateCoupon(new Coupon(), couponRequestDto);
        coupon.setId(couponId);

        couponRepository.save(coupon);

        return Optional.of(couponMapper.couponToCouponResponseDto(coupon));
    }

    public Optional<CouponResponseDto> findById(long couponId) {

        Optional<Coupon> coupon = couponRepository.findById(couponId);
        return coupon.map(couponMapper::couponToCouponResponseDto);
    }

    public List<CouponResponseDto> findAll() {

        return couponRepository.findAll().stream()
                .map(couponMapper::couponToCouponResponseDto).toList();
    }

}
