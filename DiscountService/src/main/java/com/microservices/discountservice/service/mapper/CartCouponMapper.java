package com.microservices.discountservice.service.mapper;

import com.microservices.discountservice.dto.CartCouponRequestDto;
import com.microservices.discountservice.dto.CartCouponResponseDto;
import com.microservices.discountservice.entity.CartCoupon;
import com.microservices.discountservice.exceptionHandling.InvalidAmountException;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartCouponMapper {

    CartCoupon cartCouponRequestDtoToCartCoupon(CartCouponRequestDto cartCouponRequestDto) throws InvalidAmountException;

    CartCouponResponseDto cartCouponToCartCouponResponseDto(CartCoupon cartCoupon);

    CartCoupon updateCartCoupon(@MappingTarget CartCoupon cartCoupon, CartCouponRequestDto dto);

}
