package com.microservices.productservice.repository;


import com.microservices.productservice.entity.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ListCrudRepository<Product, Long> {
    List<Product> findByIdIn(List<Long> productIds);

    List<Product> findByCategory(String category);


}