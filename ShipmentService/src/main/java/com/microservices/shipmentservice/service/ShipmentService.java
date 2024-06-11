package com.microservices.shipmentservice.service;

import com.microservices.shipmentservice.dto.request.ShipmentRequestDto;
import com.microservices.shipmentservice.dto.response.ShipmentResponseDto;
import com.microservices.shipmentservice.exceptionhandling.GeneralException;
import com.microservices.shipmentservice.exceptionhandling.ShipmentException;
import com.microservices.shipmentservice.repository.ShipmentRepository;
import com.microservices.shipmentservice.service.kafka.producer.ShipmentProducer;
import com.microservices.shipmentservice.service.mapper.ShipmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.microservices.shipmentservice.entity.Shipment;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentMapper shipmentMapper;
    private final ShipmentProducer shipmentProducer;

    public ShipmentResponseDto process(ShipmentRequestDto shipmentRequestDto)
            throws ShipmentException {

        Shipment shipment = shipmentMapper.toShipment(shipmentRequestDto);

        // To test compensating actions
        // Initial point of reverse saga
        if (shipmentRequestDto.productShipments().size() > 10)
            throw new ShipmentException("A shipment unit could not contain more than 10 unit");

        shipmentRepository.save(shipment);
        shipmentProducer.sendShipmentProcessedEventToKafka(shipmentMapper.toShipmentProcessedEvent(shipment));

        return shipmentMapper.toShipmentResponseDto(shipment);

//         catch (Exception ex) {
//            shipmentProducer.sendShipmentCancelledEventToKafka(shipmentMapper.toShipmentCancelledEvent(shipment));
//            throw new GeneralException("An exception occurred during shipment process!");
//        }
    }

    public void update(long shipmentId, ShipmentRequestDto shipmentRequestDto) {

        Shipment shipment = shipmentRepository.findById(shipmentId)
                .map(i -> shipmentMapper.updateShipment(i, shipmentRequestDto))
                .orElseThrow(() -> new GeneralException("Shipment not found for shipmentId: " + shipmentId));

        shipmentRepository.save(shipment);
    }

    public void delete(long shipmentId) {

        if (!shipmentRepository.existsById(shipmentId))
            throw new GeneralException("Shipment entry not found for shipmentId: " + shipmentId);

        shipmentRepository.deleteById(shipmentId);
    }

    public ShipmentResponseDto findById(long shipmentId) {

        return shipmentRepository.findById(shipmentId)
                .map(shipmentMapper::toShipmentResponseDto)
                .orElseThrow(() -> new GeneralException("Shipment entry not found for shipment Id: " + shipmentId));
    }

    public boolean checkIfShipmentIdIsValid(long shipmentId) {
        return shipmentRepository.existsById(shipmentId);
    }

    public boolean checkIfAllShipmentIdsAreValid(List<Long> shipmentId) {
        return shipmentRepository.existsAllByShipmentIdIsIn(shipmentId);
    }
}