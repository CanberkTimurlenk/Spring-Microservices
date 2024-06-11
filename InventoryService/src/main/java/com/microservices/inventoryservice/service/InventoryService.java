package com.microservices.inventoryservice.service;

import com.microservices.inventoryservice.dto.request.InventoryRequestDto;
import com.microservices.inventoryservice.dto.request.StockDecrementDto;
import com.microservices.inventoryservice.dto.response.InventoryResponseDto;
import com.microservices.inventoryservice.entity.Inventory;
import com.microservices.inventoryservice.event.stockupdated.InventoryProduct;
import com.microservices.inventoryservice.exceptionhandling.GeneralException;
import com.microservices.inventoryservice.exceptionhandling.InventoryException;
import com.microservices.inventoryservice.repository.InventoryRepository;
import com.microservices.inventoryservice.service.mapper.InventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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
                .map(i -> inventoryMapper.updateInventory(i, inventoryRequestDto))
                .orElseThrow(() -> new GeneralException("Inventory not found for productId: " + productId));

        inventoryRepository.save(inventory);
    }

    public void delete(Long productId) {

        if (!inventoryRepository.existsById(productId))
            throw new GeneralException("Inventory entry not found for productId: " + productId);

        inventoryRepository.deleteById(productId);
    }

    public InventoryResponseDto findById(Long id) {

        return inventoryRepository.findById(id)
                .map(inventoryMapper::toInventoryResponseDto)
                .orElseThrow(() -> new GeneralException("Inventory entry not found for inventory Id: " + id));
    }

    public InventoryResponseDto findByProductId(Long productId) {

        return inventoryRepository.findInventoryByProductId(productId)
                .map(inventoryMapper::toInventoryResponseDto)
                .orElseThrow(() -> new GeneralException("Inventory entry not found for product Id: " + productId));
    }

    public List<InventoryResponseDto> findOutOfInventoryItems() {

        return inventoryRepository.findOutOfInventoryItems()
                .stream()
                .map(inventoryMapper::toInventoryResponseDto)
                .toList();
    }

    public List<InventoryProduct> stockDecrement(List<StockDecrementDto> stockDecrementDtoList)
            throws InventoryException {

        // Initialize an empty ArrayList to create StockUpdatedEvent
        List<InventoryProduct> inventoryProductList = new ArrayList<>();

        for (StockDecrementDto sd : stockDecrementDtoList) {

            Inventory inventory = inventoryRepository.findInventoryByProductId(sd.productId())
                    .orElseThrow(() ->
                            new GeneralException("Inventory entry not found for product Id: "
                                    + sd.productId()));

            if (inventory.getStockAmount() > 0
                    && inventory.getStockAmount() - sd.quantity() > 0) {
                // Execute this block if the final stock amount is non-negative

                // Deduct the quantity specified in the order from the current stock amount
                inventory.setStockAmount(inventory.getStockAmount() - sd.quantity());
                inventoryRepository.save(inventory);

                // Add initial and final stock of specified product to the inventoryProductList
                inventoryProductList.add(new InventoryProduct(sd.productId(), inventory.getStockAmount(), inventory.getStockAmount() - sd.quantity()));

            }
            else {
                throw new InventoryException(sd.productId());
            }
        }
        return inventoryProductList;
    }

}