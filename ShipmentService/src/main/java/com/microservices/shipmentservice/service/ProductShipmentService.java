package com.microservices.shipmentservice.service;

import com.microservices.shipmentservice.dto.request.ProductShipmentRequestDto;
import com.microservices.shipmentservice.exceptionhandling.GeneralException;
import com.microservices.shipmentservice.repository.ProductShipmentRepository;
import com.microservices.shipmentservice.service.mapper.ProductShipmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductShipmentService {

    private final ProductShipmentRepository productShipmentRepository;
    private final ProductShipmentMapper productShipmentMapper;
    private final ShipmentService shipmentService;

    // TODO: Review this method

//    public List<ProductShipmentResponseDto> findProductShipmentsByProductId(long productId) {
//
//        return productShipmentRepository.findProductShipmentsByProductId(productId)
//                .stream()
//                .map(productShipmentMapper::toProductShipmentResponseDto)
//                .toList();
//    }
//
//    public void save(List<ProductShipmentRequestDto> productShipmentRequestDtos) {
//
//        if (shipmentService.checkIfAllShipmentIdsAreValid(
//                productShipmentRequestDtos
//                        .stream()
//                        .map(ProductShipmentRequestDto::).toList())) {
//            throw new GeneralException("Insertion of a new product shipment is only available for existing shipments.");
//        }
//
//        productShipmentRepository.saveAll(
//                productShipmentRequestDtos
//                        .stream()
//                        .map(productShipmentMapper::toProductShipment).toList());
//    }

    public void delete(long productShipmentId) {

        if (!productShipmentRepository.existsById(productShipmentId))
            throw new GeneralException("Product Shipment entry not found for product shipment shipmentId: " + productShipmentId);

        productShipmentRepository.deleteById(productShipmentId);
    }

    public void update(long productShipmentId, ProductShipmentRequestDto productShipmentRequestDto) {

        productShipmentRepository.findById(productShipmentId)
                .ifPresentOrElse
                        (productShipment ->
                                {
                                    var productShipmentToUpdate = productShipmentMapper.updateProductShipment(productShipment, productShipmentRequestDto);
                                    productShipmentRepository.save(productShipmentToUpdate);
                                },
                                () -> {
                                    throw new GeneralException("Product Shipment does not exists with shipmentId: " + productShipmentId);
                                });

    }

}
