package com.microservices.orderservice.service;

import com.microservices.orderservice.dto.response.external.ProductResponseDto;
import com.microservices.orderservice.entity.Order;
import com.microservices.orderservice.entity.OrderProduct;
import com.microservices.orderservice.repository.OrderProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    @Transactional
    public void saveAll(List<ProductResponseDto> products, Order order) {
        var orderProducts = products.stream()
                .map(p -> {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setProductId(p.id());
                    orderProduct.setOrder(order);
                    return orderProduct;
                }).toList();

        orderProductRepository.saveAll(orderProducts);
        order.setOrderProducts(new HashSet<>(orderProducts));

    }
}
