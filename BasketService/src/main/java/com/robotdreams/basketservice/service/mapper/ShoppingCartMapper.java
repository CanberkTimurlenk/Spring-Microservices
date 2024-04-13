package com.robotdreams.basketservice.service.mapper;

import com.robotdreams.basketservice.dto.CartItemRequestDto;
import com.robotdreams.basketservice.dto.ShoppingCartRequestDto;
import com.robotdreams.basketservice.dto.ShoppingCartResponseDto;
import com.robotdreams.basketservice.entity.CartItem;
import com.robotdreams.basketservice.entity.ShoppingCart;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ShoppingCartMapper {

    @Mapping(target = "items", source = "items", qualifiedByName = "mapToCartItem")
    ShoppingCart shoppingCartRequestDtoToShoppingCart(ShoppingCartRequestDto shoppingCartRequestDto);
    ShoppingCartResponseDto shoppingCartToShoppingCartResponseDto(ShoppingCart shoppingCart);

    @Named("mapToCartItem")
    default CartItem mapToProductDetail(CartItemRequestDto dto) {
        return CartItemMapper.INSTANCE.cartItemRequestDtoToCartItem(dto);
    }

}
