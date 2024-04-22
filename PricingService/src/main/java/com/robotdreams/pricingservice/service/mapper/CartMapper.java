package com.robotdreams.pricingservice.service.mapper;

import com.robotdreams.pricingservice.dto.cart.request.PricedCartRequestDto;
import com.robotdreams.pricingservice.dto.cart.request.PricedCartItemRequestDto;
import com.robotdreams.pricingservice.dto.cart.response.PricedCartResponseDto;
import com.robotdreams.pricingservice.dto.cart.response.PricedCartItemResponseDto;
import com.robotdreams.pricingservice.entity.Cart;
import com.robotdreams.pricingservice.entity.CartItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartMapper {

    //    @Mapping(target = "cartItems", source = "cartItems", qualifiedByName = "mapToCartItem")

    //    Cart toCart(PricedCartRequestDto pricedCartRequestDto);
//
    @Mapping(target = "cartItems", source = "cartItems", qualifiedByName = "mapToPricedCartItem")
    PricedCartResponseDto toPricedCartResponseDto(Cart cart);

    @Named("mapToPricedCartItem")
    default List<PricedCartItemResponseDto> mapToPricedCartItemResponseDto(List<CartItem> dtos) {
        return dtos.stream().map(CartItemMapper.INSTANCE::toPricedCartItemResponseDto).toList();
    }

//    @Named("mapToPricedCartItem")
//    default List<CartItem> mapToProduct(List<PricedCartItemRequestDto> dtos) {
//        return dtos.stream().map(CartItemMapper.INSTANCE::toCartItem).toList();
//    }

}