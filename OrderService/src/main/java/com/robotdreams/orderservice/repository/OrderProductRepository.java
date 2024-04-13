package com.robotdreams.orderservice.repository;

import com.robotdreams.orderservice.entity.OrderProduct;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends ListCrudRepository<OrderProduct, Long> {

}
