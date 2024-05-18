package com.robotdreams.discountservice.service.mapper;

import com.robotdreams.discountservice.dto.CartCouponRequestDto;
import com.robotdreams.discountservice.dto.CartCouponResponseDto;
import com.robotdreams.discountservice.entity.CartCoupon;
import com.robotdreams.discountservice.exceptionHandling.InvalidAmountException;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartCouponMapper {

    CartCoupon cartCouponRequestDtoToCartCoupon(CartCouponRequestDto cartCouponRequestDto) throws InvalidAmountException;

    CartCouponResponseDto cartCouponToCartCouponResponseDto(CartCoupon cartCoupon);

    CartCoupon updateCartCoupon(@MappingTarget CartCoupon cartCoupon, CartCouponRequestDto dto);

}
