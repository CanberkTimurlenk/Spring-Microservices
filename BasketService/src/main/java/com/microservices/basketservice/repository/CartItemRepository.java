package com.microservices.basketservice.repository;

import com.microservices.basketservice.entity.CartItem;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends ListCrudRepository<CartItem,Long> {

}
