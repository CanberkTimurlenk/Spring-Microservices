package com.microservices.shipmentservice.service.mapper;

import com.microservices.shipmentservice.dto.request.ShipmentRequestDto;
import com.microservices.shipmentservice.dto.response.ShipmentResponseDto;
import com.microservices.shipmentservice.entity.Shipment;
import com.microservices.shipmentservice.event.shipmentcancelled.ShipmentCancelledEvent;
import com.microservices.shipmentservice.event.shipmentprocessed.ShipmentProcessedEvent;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ShipmentMapper {

    Shipment toShipment(ShipmentRequestDto shipmentRequestDto);

    ShipmentResponseDto toShipmentResponseDto(Shipment shipment);

    Shipment updateShipment(@MappingTarget Shipment shipment, ShipmentRequestDto shipmentRequestDto);

    @Mapping(source = "shipment.productShipments", target = "processedProductShipments")
    ShipmentProcessedEvent toShipmentProcessedEvent(Shipment shipment);

    @Mapping(source = "shipmentResponseDto.productShipments", target = "processedProductShipments")
    ShipmentProcessedEvent toShipmentProcessedEvent(ShipmentResponseDto shipmentResponseDto,long orderId);


    ShipmentCancelledEvent toShipmentCancelledEvent(Shipment shipment);

}
