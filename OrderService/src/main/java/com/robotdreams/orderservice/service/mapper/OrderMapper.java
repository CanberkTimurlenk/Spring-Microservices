package com.robotdreams.orderservice.service.mapper;

import com.robotdreams.orderservice.dto.request.OrderRequestDto;
import com.robotdreams.orderservice.entity.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import com.robotdreams.orderservice.dto.response.internal.OrderResponseDto;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {


    Order OrderRequestDtoToOrder(OrderRequestDto orderRequestDto);

    @Mapping(target = "orderId", source = "id")
    OrderResponseDto orderToOrderResponseDto(Order order);
}
