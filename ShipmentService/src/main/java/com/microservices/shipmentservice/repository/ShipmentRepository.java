package com.microservices.shipmentservice.repository;

import com.microservices.shipmentservice.entity.Shipment;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends ListCrudRepository<Shipment, Long> {
    boolean existsAllByShipmentIdIsIn(List<Long> shipmentId);
}
