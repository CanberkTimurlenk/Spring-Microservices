package com.microservices.shipmentservice.repository;

import com.microservices.shipmentservice.entity.ProductShipment;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductShipmentRepository extends ListCrudRepository<ProductShipment, Long> {

    List<ProductShipment> findProductShipmentsByProductId(long productId);

}
