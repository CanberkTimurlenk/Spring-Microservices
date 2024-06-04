package com.microservices.shipmentservice.service;

import com.microservices.shipmentservice.dto.request.ShipmentRequestDto;
import com.microservices.shipmentservice.dto.response.ShipmentResponseDto;
import com.microservices.shipmentservice.exceptionHandling.GeneralException;
import com.microservices.shipmentservice.repository.ShipmentRepository;
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

    public void process(ShipmentRequestDto shipmentRequestDto) {

        Shipment shipment = shipmentMapper.toShipment(shipmentRequestDto);
        shipmentRepository.save(shipment);
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