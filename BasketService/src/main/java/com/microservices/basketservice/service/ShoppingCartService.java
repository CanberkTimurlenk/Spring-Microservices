package com.microservices.basketservice.service;

import com.microservices.basketservice.dto.ShoppingCartRequestDto;
import com.microservices.basketservice.dto.ShoppingCartResponseDto;
import com.microservices.basketservice.entity.ShoppingCart;
import com.microservices.basketservice.feign.UserFeignClient;
import com.microservices.basketservice.repository.ShoppingCartRepository;
import com.microservices.basketservice.service.mapper.ShoppingCartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserFeignClient userFeignClient;

    public Optional<ShoppingCartResponseDto> findShoppingCartByUserId(long userId) {

        return shoppingCartRepository.findShoppingCartByUserId(userId)
                .map(shoppingCartMapper::shoppingCartToShoppingCartResponseDto);
    }

    public Optional<ShoppingCartResponseDto> findById(long userId) {

        return shoppingCartRepository.findById(userId)
                .map(shoppingCartMapper::shoppingCartToShoppingCartResponseDto);
    }

    public List<ShoppingCartResponseDto> findAll() {

        return shoppingCartRepository.findAll()
                .stream()
                .map(shoppingCartMapper::shoppingCartToShoppingCartResponseDto)
                .toList();
    }

    public boolean deleteById(long userId) {

        if (!shoppingCartRepository.existsById(userId))
            return false;

        shoppingCartRepository.deleteById(userId);
        return true;
    }

    public long save(ShoppingCartRequestDto shoppingCartRequestDto) {

        ShoppingCart shoppingCart = shoppingCartMapper.shoppingCartRequestDtoToShoppingCart(shoppingCartRequestDto);

        shoppingCart.getItems()
                .forEach(ci -> ci.setId(UUID.randomUUID().toString()));

        return shoppingCartRepository.save(shoppingCart).getUserId();
    }

    public Optional<ShoppingCartResponseDto> update(long userId, ShoppingCartRequestDto shoppingCartRequestDto) {


        if (shoppingCartRepository.existsById(userId)) {
            ShoppingCart shoppingCart = shoppingCartMapper.shoppingCartRequestDtoToShoppingCart(shoppingCartRequestDto);
            shoppingCart.setUserId(userId);

            shoppingCartRepository.save(shoppingCart);
            return Optional.of(shoppingCartMapper.shoppingCartToShoppingCartResponseDto(shoppingCart));
        }
        return Optional.empty();
    }
}