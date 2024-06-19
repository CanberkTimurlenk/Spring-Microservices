package com.microservices.orderservice.service.mapper;

import com.microservices.orderservice.dto.request.OrderRequestDto;
import com.microservices.orderservice.dto.request.ProductRequestDto;
import com.microservices.orderservice.dto.response.internal.OrderResponseDto;
import com.microservices.orderservice.entity.Order;
import com.microservices.orderservice.entity.OrderProduct;
import com.microservices.orderservice.event.ordercreated.OrderCreatedEvent;
import com.microservices.orderservice.event.ordercreated.Product;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {


    @Mapping(target = "orderProducts", source = "productRequestDtoSet", qualifiedByName = "mapToOrderProduct")
    Order toOrder(OrderRequestDto orderRequestDto);

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "products", source = "orderProducts", qualifiedByName = "mapToProduct")
    OrderResponseDto toOrderResponseDto(Order order);

    OrderCreatedEvent toOrderCreatedEvent(OrderResponseDto orderResponseDto);

    @AfterMapping
    default void setOrder(@MappingTarget Order order, OrderRequestDto orderRequestDto) {
        order.getOrderProducts().forEach(op -> op.setOrder(order));
    }

    @Named("mapToProduct")
    default Set<Product> mapToProduct(Set<OrderProduct> orderProducts) {
        return orderProducts.stream()
                .map(op -> new Product(op.getProductId(), op.getQuantity(), op.getPrice()))
                .collect(Collectors.toSet());
    }

    @Named("mapToOrderProduct")
    default Set<OrderProduct> mapToOrderProduct(Set<ProductRequestDto> productRequestDtos) {
        return productRequestDtos.stream()
                .map(pr -> {
                    var orderProduct = new OrderProduct();
                    orderProduct.setProductId(pr.productId());
                    orderProduct.setQuantity(pr.quantity());
                    return orderProduct;

                })
                .collect(Collectors.toSet());
    }


}
