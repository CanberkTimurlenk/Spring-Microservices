package com.robotdreams.pricingservice.service.mapper;


import com.robotdreams.pricingservice.dto.pricecontainer.response.ContainerItemResponseDto;

import com.robotdreams.pricingservice.entity.ContainerItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContainerItemMapper {

//    ContainerItem toContainerItem(ContainerItemRequestDto dto);

    ContainerItemResponseDto toContainerItemResponseDto(ContainerItem cartItem);

    ContainerItemMapper INSTANCE = Mappers.getMapper(ContainerItemMapper.class);
}