package com.microservices.basketservice.service.mapper;

import com.microservices.basketservice.dto.CartItemRequestDto;
import com.microservices.basketservice.dto.CartItemResponseDto;
import com.microservices.basketservice.entity.CartItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartItemMapper {

    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);


    CartItemResponseDto cartItemToCartItemResponseDto(CartItem cartItem);

    CartItem cartItemRequestDtoToCartItem(CartItemRequestDto cartItemRequestDto);
}
