package com.microservices.shipmentservice.controller;

import com.microservices.shipmentservice.dto.request.ProductShipmentRequestDto;
import com.microservices.shipmentservice.dto.response.ProductShipmentResponseDto;
import com.microservices.shipmentservice.service.ProductShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-shipments")
@RequiredArgsConstructor
public class ProductShipmentController {

    private final ProductShipmentService productShipmentService;

//    @GetMapping
//    public ResponseEntity<List<ProductShipmentResponseDto>> findProductShipmentsByProductId(long productId) {
//        return ResponseEntity.ok(productShipmentService.(productId));
//    }
//
//    @PostMapping
//    public ResponseEntity<Void> save(List<ProductShipmentRequestDto> productShipmentRequestDtos) {
//        productShipmentService.save(productShipmentRequestDtos);
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping
    public ResponseEntity<Void> delete(long productShipmentId) {
        productShipmentService.delete(productShipmentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{product-shipment-id}")
    public ResponseEntity<Void> update(@PathVariable("product-shipment-id") long productShipmentId, ProductShipmentRequestDto productShipmentRequestDto) {
        productShipmentService.update(productShipmentId, productShipmentRequestDto);
        return ResponseEntity.ok().build();
    }
}