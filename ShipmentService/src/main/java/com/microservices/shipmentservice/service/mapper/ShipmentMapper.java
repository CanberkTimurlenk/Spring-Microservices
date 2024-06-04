package com.microservices.shipmentservice.service.mapper;

import com.microservices.shipmentservice.dto.request.ShipmentRequestDto;
import com.microservices.shipmentservice.dto.response.ShipmentResponseDto;
import com.microservices.shipmentservice.entity.Shipment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ShipmentMapper {

    Shipment toShipment(ShipmentRequestDto shipmentRequestDto);

    ShipmentResponseDto toShipmentResponseDto(Shipment shipment);

    Shipment updateShipment(@MappingTarget Shipment shipment, ShipmentRequestDto shipmentRequestDto);

}
