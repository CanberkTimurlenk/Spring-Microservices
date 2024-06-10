package com.microservices.inventoryservice.repository;

import com.microservices.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends ListCrudRepository<Inventory, Long> {

    // Native Query
    @Query(value = "SELECT * FROM Inventories WHERE stockAmount = 0", nativeQuery = true)
    List<Inventory> findOutOfInventoryItems();

    Optional<Inventory> findInventoryByProductId(long productId);

}
