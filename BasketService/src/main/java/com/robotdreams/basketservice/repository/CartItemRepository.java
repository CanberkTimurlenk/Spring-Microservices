package com.robotdreams.basketservice.repository;

import com.robotdreams.basketservice.entity.CartItem;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends ListCrudRepository<CartItem,Long> {

}
