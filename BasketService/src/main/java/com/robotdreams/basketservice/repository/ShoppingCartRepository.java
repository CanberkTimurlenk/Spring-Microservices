package com.robotdreams.basketservice.repository;

import com.robotdreams.basketservice.entity.ShoppingCart;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends ListCrudRepository<ShoppingCart, Long> {
    ShoppingCart findShoppingCartByUserId(long userId);
}
