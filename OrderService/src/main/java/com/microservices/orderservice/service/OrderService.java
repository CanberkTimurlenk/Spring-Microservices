package com.microservices.orderservice.service;

import com.microservices.orderservice.dto.request.OrderRequestDto;
import com.microservices.orderservice.dto.request.ProductRequestDto;
import com.microservices.orderservice.dto.response.external.ProductResponseDto;
import com.microservices.orderservice.dto.response.external.UserInfoResponseDto;
import com.microservices.orderservice.dto.response.internal.OrderResponseDto;
import com.microservices.orderservice.entity.Order;
import com.microservices.orderservice.entity.OrderProduct;
import com.microservices.orderservice.exceptionHandling.GeneralException;
import com.microservices.orderservice.exceptionHandling.OrderException;
import com.microservices.orderservice.feign.ProductFeignClient;
import com.microservices.orderservice.feign.UserFeignClient;
import com.microservices.orderservice.repository.OrderRepository;
import com.microservices.orderservice.service.constant.ShippingConstants;
import com.microservices.orderservice.service.kafka.producer.OrderProducer;
import com.microservices.orderservice.service.mapper.OrderMapper;
import com.microservices.orderservice.service.shipping.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderProductService orderProductService;
    private final ProductFeignClient productClient;
    private final OrderProducer orderProducer;

    public void save(OrderRequestDto orderRequestDto) {

        List<Long> productIds = orderRequestDto.orderProducts()
                .stream()
                .map(ProductRequestDto::productId)
                .toList();

        // persist order
        Order order = orderMapper.toOrder(orderRequestDto);

//        for (OrderProduct orderProduct : order.getOrderProducts()) {
//            orderProduct.set
//
//        }

        // find user with ID
        // TODO: This line was commented out for test purposes
        // UserInfoResponseDto user = userClient.getInfo(orderRequestDto.userId());

        // create order number
        order.setOrderNumber(UUID.randomUUID().toString());

        // get products
        List<ProductResponseDto> products = productClient.getProductsById(productIds);

        // set shipment cost
        // TODO: This line was commented out for test purposes
        //order.setShippingCost(getShippingOffer(products, user));

        // persist order
        orderRepository.save(order);

        try {
            checkIfOrderAmountIsSufficent(products);
            checkIfOrderHasMoreThanThreeProductsInSameCategory(products);
        } catch (OrderException e) {
            logger.error("an exception {}", e.getMessage());
            throw new GeneralException("exception occured" + e.getMessage());
        }

        // persist orderProducts
        orderProductService.saveAll(products, order);

        orderProducer.sendOrderCreatedEventToKafka(orderMapper.toOrderCreatedEvent(order));

        // SmsSender(new HappySmsStrategy()).sendOrderCreatedSms(order, user);
    }


    public Optional<List<OrderResponseDto>> findAll() {

        List<OrderResponseDto> orderResponseDtos =
                orderRepository.findAll().stream().map(orderMapper::toOrderResponseDto).toList();

        return Optional.of(orderResponseDtos);
    }

    public void delete(long orderId) {
        orderRepository.deleteById(orderId);
    }

    private void checkIfOrderHasMoreThanThreeProductsInSameCategory(List<ProductResponseDto> products)
            throws OrderException {

        // Business kuralı: bir sipariş aynı kategoriden en fazla üç ürün içerebilir

        Map<String, Long> categoryProductCount = products.stream()
                .collect(Collectors.groupingBy(ProductResponseDto::category, Collectors.counting()));

        StringBuilder message = new StringBuilder();

        // üçten fazla aynı kategoriden olan ürün varsa isimlerini bir string içerisinde topluyorum
        categoryProductCount.forEach((category, count) -> {
            if (count > 3)
                message.append(category)
                        .append(" : ")
                        .append(count)
                        .append(" ");
        });

        if (!message.isEmpty())
            throw new OrderException(("An order must include a maximum of 3 products from a single category. Violations: " + message).trim());
    }

    private double getShippingOffer(List<ProductResponseDto> products, UserInfoResponseDto user) {

        double totalWeight = products
                .stream()
                .map(ProductResponseDto::price)
                .reduce(0.0, Double::sum);

        if (user.premium())
            return ShippingConstants.PREMIUM_USER_SHIPMENT_COST;

        return calculateCargoPrice(totalWeight);
    }

    private double calculateCargoPrice(double totalWeight) {

        if (totalWeight > 250)
            return new ShippingCostCalculator(new FedExShippingStrategy()).getCost(totalWeight);

        else if (totalWeight > 100)
            return new ShippingCostCalculator(new YurticiShippingStrategy()).getCost(totalWeight);

        return new ShippingCostCalculator(new MngShippingStrategy()).getCost(totalWeight);
    }

    private void checkIfOrderAmountIsSufficent(List<ProductResponseDto> products)
            throws OrderException {

        double minOrderAmount = 50.0;

        double total = products.stream()
                .map(ProductResponseDto::price)
                .reduce(0.0, Double::sum);

        if (minOrderAmount > total)
            throw new OrderException("The order amount of " + total + " is lower than the minimum order amount.");
    }
}
