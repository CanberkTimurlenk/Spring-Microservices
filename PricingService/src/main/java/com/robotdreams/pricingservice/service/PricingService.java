package com.robotdreams.pricingservice.service;

import com.robotdreams.pricingservice.dto.cart.request.PricedCartItemRequestDto;
import com.robotdreams.pricingservice.dto.cart.request.PricedCartRequestDto;
import com.robotdreams.pricingservice.dto.cart.response.PricedCartResponseDto;
import com.robotdreams.pricingservice.dto.user.UserResponseDto;
import com.robotdreams.pricingservice.entity.Cart;
import com.robotdreams.pricingservice.entity.CartItem;
//import com.robotdreams.pricingservice.feign.UserFeignClient;
import com.robotdreams.pricingservice.service.mapper.CartItemMapper;
import com.robotdreams.pricingservice.service.mapper.CartMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PricingService {

    //    private final UserFeignClient userFeignClient;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;

    public PricedCartResponseDto getPricedCart(PricedCartRequestDto pricedCardRequestDto) {

        //UserResponseDto userResponseDto = userFeignClient.findUserById(pricedCardRequestDto.userId());

        List<CartItem> cartItemList = new ArrayList<>();

        for (PricedCartItemRequestDto cartItem : pricedCardRequestDto.cartItems()) {

            cartItemList.add(CartItem.builder()
                    .productId(cartItem.productId())
                    .unitPrice(cartItem.unitPrice())
                    .discountCode(cartItem.discountCode())
                    .quantity(cartItem.quantity())
                    .build());
        }

        Cart cart = Cart.builder()
                .userId(pricedCardRequestDto.userId())
                .cartItems(cartItemList)
//                .isPremiumCart(userResponseDto.premium())
                .isPremiumCart(false)
                .discountCode(pricedCardRequestDto.discountCode())
                .build();

        return cartMapper.toPricedCartResponseDto(cart);
    }
}