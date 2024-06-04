package com.microservices.shipmentservice.service.mapper;

import com.microservices.shipmentservice.dto.request.ProductShipmentRequestDto;
import com.microservices.shipmentservice.dto.response.ProductShipmentResponseDto;
import com.microservices.shipmentservice.entity.ProductShipment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductShipmentMapper {

    ProductShipment toProductShipment(ProductShipmentRequestDto productShipmentRequestDto);

    ProductShipmentResponseDto toProductShipmentResponseDto(ProductShipment productShipment);

    ProductShipment updateProductShipment(@MappingTarget ProductShipment productShipment, ProductShipmentRequestDto productShipmentRequestDto);

}