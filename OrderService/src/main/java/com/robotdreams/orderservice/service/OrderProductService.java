package com.robotdreams.orderservice.service;

import com.robotdreams.orderservice.dto.response.external.ProductResponseDto;
import com.robotdreams.orderservice.entity.Order;
import com.robotdreams.orderservice.entity.OrderProduct;
import com.robotdreams.orderservice.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public void saveAll(List<ProductResponseDto> products, Order order) {
        var orderProducts = products.stream()
                .map(p -> {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setProductId(p.id());
                    orderProduct.setOrder(order);
                    return orderProduct;
                }).toList();

        orderProductRepository.saveAll(orderProducts);

    }
}
