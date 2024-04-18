package com.robotdreams.basketservice.util;

import com.robotdreams.basketservice.dto.CartItemRequestDto;
import com.robotdreams.basketservice.dto.ShoppingCartRequestDto;
import com.robotdreams.basketservice.repository.ShoppingCartRepository;
import com.robotdreams.basketservice.service.ShoppingCartService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketTest {

    private final ShoppingCartService shoppingCartService;


    @PostConstruct
    public void save() {

        List<CartItemRequestDto> items = new ArrayList<>();

        items.add(new CartItemRequestDto(2, 1, "DISCOUNT_10"));
        items.add(new CartItemRequestDto(1, 2, "DISCOUNT_20"));

        ShoppingCartRequestDto shoppingCart = new ShoppingCartRequestDto(1, "CODE1", items);

        shoppingCartService.save(shoppingCart);

    }
}
