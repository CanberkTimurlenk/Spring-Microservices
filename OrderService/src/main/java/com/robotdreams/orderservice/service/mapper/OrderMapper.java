package com.robotdreams.orderservice.service.mapper;

import com.robotdreams.orderservice.dto.request.OrderRequestDto;
import com.robotdreams.orderservice.entity.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import com.robotdreams.orderservice.dto.response.internal.OrderResponseDto;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {

    Order OrderRequestDtoToOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto orderToOrderResponseDto(Order order);
}
