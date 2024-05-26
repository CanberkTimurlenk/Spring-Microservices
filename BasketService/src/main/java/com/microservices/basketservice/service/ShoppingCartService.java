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

    public ShoppingCartResponseDto findShoppingCartByUserId(long userId) {

        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserId(userId);
        return shoppingCartMapper.shoppingCartToShoppingCartResponseDto(shoppingCart);
    }

    public Optional<ShoppingCartResponseDto> findById(long userId) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(userId);
        return shoppingCartOptional.map(shoppingCartMapper::shoppingCartToShoppingCartResponseDto);
    }

    public List<ShoppingCartResponseDto> findAll() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        return shoppingCarts.stream()
                .map(shoppingCartMapper::shoppingCartToShoppingCartResponseDto).toList();
    }

    public boolean deleteById(long userId) {
        if (shoppingCartRepository.existsById(userId)) {
            shoppingCartRepository.deleteById(userId);
            return true;
        }
        return false;
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