package com.robotdreams.basketservice.service;

import com.robotdreams.basketservice.dto.ShoppingCartRequestDto;
import com.robotdreams.basketservice.dto.ShoppingCartResponseDto;
import com.robotdreams.basketservice.entity.ShoppingCart;
import com.robotdreams.basketservice.repository.ShoppingCartRepository;
import com.robotdreams.basketservice.service.mapper.ShoppingCartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartResponseDto findShoppingCartByUserId(long userId) {

        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserId(userId);
        return shoppingCartMapper.shoppingCartToShoppingCartResponseDto(shoppingCart);
    }

    public Optional<ShoppingCartResponseDto> updateShoppingCart(ShoppingCartRequestDto requestDto) {

        if (shoppingCartRepository.existsById(requestDto.userId()))
            return Optional.of(shoppingCartMapper.shoppingCartToShoppingCartResponseDto(
                    shoppingCartRepository.save(shoppingCartMapper.shoppingCartRequestDtoToShoppingCart(requestDto))));

        return Optional.empty();
    }


    public void save()
    {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(123);
        shoppingCart.setDiscountCode("DISCOUNT123");
        shoppingCart.setDiscountAmount(BigDecimal.TEN);
        shoppingCart.setCartTotalAmount(BigDecimal.valueOf(100));

        shoppingCartRepository.save(shoppingCart);



    }


}
