package com.microservices.shipmentservice.dto.response;

import java.util.Date;
import java.util.List;
import java.util.Set;

public record ShipmentResponseDto(long shipmentId, Date createDate, Date updateDate,
                                  List<ProductShipmentResponseDto> productShipments) {
}
