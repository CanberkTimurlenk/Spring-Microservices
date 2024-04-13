package com.robotdreams.basketservice.util;

import com.robotdreams.basketservice.service.ShoppingCartService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketTest {

    private final ShoppingCartService shoppingCartService;


    @PostConstruct
    public void save()
    {
        shoppingCartService.save();

    }
}
