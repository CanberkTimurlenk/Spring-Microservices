package com.microservices.orderservice.repository;

import com.microservices.orderservice.entity.OrderProduct;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends ListCrudRepository<OrderProduct, Long> {

}
