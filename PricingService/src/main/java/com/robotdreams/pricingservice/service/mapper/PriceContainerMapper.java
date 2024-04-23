package com.robotdreams.pricingservice.service.mapper;

import com.robotdreams.pricingservice.dto.pricecontainer.response.PriceContainerResponseDto;
import com.robotdreams.pricingservice.dto.pricecontainer.response.ContainerItemResponseDto;
import com.robotdreams.pricingservice.entity.PriceContainer;
import com.robotdreams.pricingservice.entity.ContainerItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PriceContainerMapper {

    //    @Mapping(target = "cartItems", source = "cartItems", qualifiedByName = "mapToContainerItem")

    //    PriceContainer toPriceContainer(PriceContainerRequestDto pricedPriceContainerRequestDto);
//
    @Mapping(target = "containerItems", source = "containerItems", qualifiedByName = "mapToContainerItem")
    PriceContainerResponseDto toPriceContainerResponseDto(PriceContainer cart);

    @Named("mapToContainerItem")
    default List<ContainerItemResponseDto> mapToContainerItemResponseDto(List<ContainerItem> dtos) {
        return dtos.stream().map(ContainerItemMapper.INSTANCE::toContainerItemResponseDto).toList();
    }

//    @Named("mapToContainerItem")
//    default List<ContainerItem> mapToProduct(List<ContainerItemRequestDto> dtos) {
//        return dtos.stream().map(ContainerItemMapper.INSTANCE::toContainerItem).toList();
//    }

}