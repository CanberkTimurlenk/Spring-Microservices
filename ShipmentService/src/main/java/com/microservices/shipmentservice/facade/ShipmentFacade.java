package com.microservices.shipmentservice.facade;

import com.microservices.shipmentservice.dto.request.ProductShipmentRequestDto;
import com.microservices.shipmentservice.dto.request.ShipmentRequestDto;
import com.microservices.shipmentservice.event.shipmentcancelled.ShipmentCancelledEvent;
import com.microservices.shipmentservice.exceptionhandling.GeneralException;
import com.microservices.shipmentservice.exceptionhandling.ShipmentException;
import com.microservices.shipmentservice.service.ShipmentService;
import com.microservices.shipmentservice.service.kafka.producer.ShipmentProducer;
import com.microservices.shipmentservice.service.mapper.ShipmentMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentFacade {

    private static final Logger logger = LoggerFactory.getLogger(ShipmentFacade.class);

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
            logger.error("{} : occurred while executing order saga", e.getMessage());
        }
    }
}
