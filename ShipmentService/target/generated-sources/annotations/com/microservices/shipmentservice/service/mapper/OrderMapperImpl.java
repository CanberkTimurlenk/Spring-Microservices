package com.microservices.shipmentservice.service.mapper;

import com.microservices.shipmentservice.dto.request.OrderRequestDto;
import com.microservices.shipmentservice.dto.response.internal.OrderResponseDto;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-10T01:12:01+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order OrderRequestDtoToOrder(OrderRequestDto orderRequestDto) {
        if ( orderRequestDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setDescription( orderRequestDto.description() );
        if ( orderRequestDto.userId() != null ) {
            order.setUserId( orderRequestDto.userId() );
        }

        return order;
    }

    @Override
    public OrderResponseDto orderToOrderResponseDto(Order order) {
        if ( order == null ) {
            return null;
        }

        long orderId = 0L;
        Date createDate = null;
        Date updateDate = null;
        long userId = 0L;
        BigDecimal shippingCost = null;
        String description = null;

        orderId = order.getId();
        createDate = order.getCreateDate();
        updateDate = order.getUpdateDate();
        userId = order.getUserId();
        shippingCost = BigDecimal.valueOf( order.getShippingCost() );
        description = order.getDescription();

        String serverTime = null;

        OrderResponseDto orderResponseDto = new OrderResponseDto( createDate, updateDate, serverTime, orderId, userId, shippingCost, description );

        return orderResponseDto;
    }
}
