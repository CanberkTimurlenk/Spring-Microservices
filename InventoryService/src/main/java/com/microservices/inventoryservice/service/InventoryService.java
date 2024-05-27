package com.microservices.inventoryservice.service;

import com.microservices.inventoryservice.dto.request.InventoryRequestDto;
import com.microservices.inventoryservice.dto.response.InventoryResponseDto;
import com.microservices.inventoryservice.entity.Inventory;
import com.microservices.inventoryservice.exceptionHandling.GeneralException;
import com.microservices.inventoryservice.repository.InventoryRepository;
import com.microservices.inventoryservice.service.mapper.InventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public void save(InventoryRequestDto inventoryRequestDto) {

        Inventory inventory = inventoryMapper.toInventory(inventoryRequestDto);
        inventoryRepository.save(inventory);
    }

    public void update(Long productId, InventoryRequestDto inventoryRequestDto) {

        Inventory inventory = inventoryRepository.findInventoryByProductId(productId)
                .orElseThrow(() -> new GeneralException("Inventory not found for productId: " + productId));

        inventoryMapper.updateInventory(inventory, inventoryRequestDto);
        inventoryRepository.save(inventory);
    }

    public void delete(Long productId) {

        if (!inventoryRepository.existsById(productId))
            throw new GeneralException("Inventory entry not found for productId: " + productId);

        inventoryRepository.deleteById(productId);
    }

    public InventoryResponseDto findById(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Inventory entry not found for inventory Id: " + id));

        return inventoryMapper.toInventoryResponseDto(inventory);
    }

    public void findByProductId(Long productId) {
        // find inventory entry by productId
    }

    public void findOutOfInventoryItems() {
        // find out of inventory items
    }

}