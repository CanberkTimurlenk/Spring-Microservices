package com.microservices.inventoryservice.service.mapper;

import com.microservices.inventoryservice.dto.request.InventoryRequestDto;
import com.microservices.inventoryservice.dto.response.InventoryResponseDto;
import com.microservices.inventoryservice.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface InventoryMapper {

    Inventory toInventory(InventoryRequestDto inventoryRequestDto);

    InventoryResponseDto toInventoryResponseDto(Inventory inventory);

    Inventory updateInventory(@MappingTarget Inventory inventory, InventoryRequestDto inventoryRequestDto);
}
