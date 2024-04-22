package com.robotdreams.pricingservice.service.mapper;


import com.robotdreams.pricingservice.dto.cart.request.PricedCartItemRequestDto;
import com.robotdreams.pricingservice.dto.cart.response.PricedCartItemResponseDto;

import com.robotdreams.pricingservice.entity.CartItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartItemMapper {

//    CartItem toCartItem(PricedCartItemRequestDto dto);

    PricedCartItemResponseDto toPricedCartItemResponseDto(CartItem cartItem);

    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);
}