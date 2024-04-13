package com.robotdreams.basketservice.service.mapper;

import com.robotdreams.basketservice.dto.CartItemRequestDto;
import com.robotdreams.basketservice.dto.CartItemResponseDto;
import com.robotdreams.basketservice.entity.CartItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartItemMapper {

    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);


    CartItemResponseDto cartItemToCartItemResponseDto(CartItem cartItem);

    CartItem cartItemRequestDtoToCartItem(CartItemRequestDto cartItemRequestDto);
}
