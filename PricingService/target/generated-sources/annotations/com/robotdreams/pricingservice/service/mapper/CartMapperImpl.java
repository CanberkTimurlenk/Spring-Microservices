package com.robotdreams.pricingservice.service.mapper;

import com.robotdreams.pricingservice.dto.cart.response.PricedCartItemResponseDto;
import com.robotdreams.pricingservice.dto.cart.response.PricedCartResponseDto;
import com.robotdreams.pricingservice.entity.Cart;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-22T23:11:46+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public PricedCartResponseDto toPricedCartResponseDto(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        List<PricedCartItemResponseDto> cartItems = null;
        long userId = 0L;
        BigDecimal cartTotalPrice = null;

        cartItems = mapToPricedCartItemResponseDto( cart.getCartItems() );
        userId = cart.getUserId();
        cartTotalPrice = cart.getCartTotalPrice();

        boolean isPremiumCart = false;

        PricedCartResponseDto pricedCartResponseDto = new PricedCartResponseDto( userId, cartItems, cartTotalPrice, isPremiumCart );

        return pricedCartResponseDto;
    }
}
