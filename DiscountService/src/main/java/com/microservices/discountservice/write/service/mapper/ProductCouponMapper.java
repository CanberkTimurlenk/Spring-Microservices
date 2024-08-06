package com.microservices.discountservice.write.service.mapper;

import com.microservices.discountservice.dto.ProductCouponRequestDto;
import com.microservices.discountservice.dto.ProductCouponResponseDto;
import com.microservices.discountservice.entity.ProductCoupon;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductCouponMapper {

    ProductCoupon productCouponRequestDtoToProductCoupon(ProductCouponRequestDto productCouponRequestDto);

    ProductCouponResponseDto productCouponToProductCouponResponseDto(ProductCoupon productCoupon);

    ProductCoupon updateProductCoupon(@MappingTarget ProductCoupon productCoupon, ProductCouponRequestDto dto);

}