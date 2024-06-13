package com.microservices.shipmentservice.controller;

import com.microservices.shipmentservice.dto.request.ShipmentRequestDto;
import com.microservices.shipmentservice.dto.response.ShipmentResponseDto;
import com.microservices.shipmentservice.exceptionhandling.ShipmentException;
import com.microservices.shipmentservice.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping
    public ResponseEntity<Void> process(ShipmentRequestDto shipmentRequestDto) {

        try {
            shipmentService.process(shipmentRequestDto);
        } catch (ShipmentException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{shipment-id}")
    public ResponseEntity<Void> update(@PathVariable("shipment-id") long shipmentId
            , ShipmentRequestDto shipmentRequestDto) {

        shipmentService.update(shipmentId, shipmentRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{shipment-id}")
    public ResponseEntity<Void> delete(@PathVariable("shipment-id") long shipmentId) {

        shipmentService.delete(shipmentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{shipment-id}")
    public ResponseEntity<ShipmentResponseDto> findById(@PathVariable("shipment-id") long shipmentId) {

        return ResponseEntity.ok(shipmentService.findById(shipmentId));
    }

}