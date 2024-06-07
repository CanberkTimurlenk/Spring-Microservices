package com.microservices.basketservice.repository;

import com.microservices.basketservice.entity.ShoppingCart;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends ListCrudRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findShoppingCartByUserId(long userId);
}
