package com.microservices.basketservice.repository;

import com.microservices.basketservice.entity.ShoppingCart;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends ListCrudRepository<ShoppingCart, Long> {
    ShoppingCart findShoppingCartByUserId(long userId);
}
