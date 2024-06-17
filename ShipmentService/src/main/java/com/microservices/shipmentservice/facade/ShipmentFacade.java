package com.microservices.shipmentservice.facade;

import com.microservices.shipmentservice.dto.request.ShipmentRequestDto;
import com.microservices.shipmentservice.event.shipmentcancelled.ShipmentCancelledEvent;
import com.microservices.shipmentservice.exceptionhandling.ShipmentException;
import com.microservices.shipmentservice.service.ShipmentService;
import com.microservices.shipmentservice.service.kafka.producer.ShipmentProducer;
import com.microservices.shipmentservice.service.mapper.ShipmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShipmentFacade {

    private final ShipmentService shipmentService;
    private final ShipmentProducer shipmentProducer;
    private final ShipmentMapper shipmentMapper;

    public void processShipment(ShipmentRequestDto shipmentRequestDto, long orderId) {

        try {
            var shipment = shipmentService.process(shipmentRequestDto);

            shipmentProducer.sendShipmentProcessedEventToKafka(
                    shipmentMapper.toShipmentProcessedEvent(shipment, orderId));

        } catch (ShipmentException e) {
            shipmentProducer.sendShipmentCancelledEventToKafka(new ShipmentCancelledEvent(orderId, shipmentRequestDto.productShipments(), new Date()));
            log.error("{} : occurred while executing order saga", e.getMessage());
        }
    }
}
