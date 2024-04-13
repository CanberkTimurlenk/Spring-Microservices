package com.robotdreams.discountservice.service.mapper;

import com.robotdreams.discountservice.dto.CouponRequestDto;
import com.robotdreams.discountservice.dto.CouponResponseDto;
import com.robotdreams.discountservice.entity.Coupon;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CouponMapper {

    Coupon couponRequestDtoToCoupon(CouponRequestDto couponRequestDto);
    CouponResponseDto couponToCouponResponseDto(Coupon coupon);

    Coupon updateCoupon(@MappingTarget Coupon coupon, CouponRequestDto dto);

}
