package com.robotdreams.orderservice.service;

import com.robotdreams.orderservice.dto.request.OrderRequestDto;
import com.robotdreams.orderservice.dto.response.external.ProductResponseDto;
import com.robotdreams.orderservice.dto.response.external.UserInfoResponseDto;
import com.robotdreams.orderservice.entity.Order;
import com.robotdreams.orderservice.exceptionHandling.GeneralException;
import com.robotdreams.orderservice.exceptionHandling.OrderException;
import com.robotdreams.orderservice.feign.ProductFeignClient;
import com.robotdreams.orderservice.feign.UserFeignClient;
import com.robotdreams.orderservice.repository.OrderRepository;
import com.robotdreams.orderservice.service.constant.ShippingConstants;
import com.robotdreams.orderservice.service.mapper.OrderMapper;
import com.robotdreams.orderservice.dto.response.internal.OrderResponseDto;
import com.robotdreams.orderservice.service.shipping.*;
import com.robotdreams.orderservice.service.sms.HappySmsStrategy;
import com.robotdreams.orderservice.service.sms.SmsSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderProductService orderProductService;
    private final UserFeignClient userClient;
    private final ProductFeignClient productClient;

    public void save(OrderRequestDto orderRequestDto) {

        List<Long> productIds = orderRequestDto.productIdList();

        // persist order
        Order order = mapper.OrderRequestDtoToOrder(orderRequestDto);

        // find user with ID
        UserInfoResponseDto user = userClient.getInfo(orderRequestDto.userId());

        // create order number
        order.setOrderNumber(UUID.randomUUID().toString());

        // get products
        List<ProductResponseDto> products = productClient.getProductsById(productIds);

        // set shipment cost
        order.setShippingCost(getShippingOffer(products, user));

        // persist order
        orderRepository.save(order);

        try {
            checkIfOrderAmountIsSufficent(products);
            checkIfOrderHasMoreThanThreeProductsInSameCategory(products);
        } catch (OrderException e) {
            System.out.println("an exception" + e.getMessage());
            throw new GeneralException("exception occured" + e.getMessage());
        }

        // persist orderProducts
        orderProductService.saveAll(products, order);


        new SmsSender(new HappySmsStrategy()).sendOrderCreatedSms(order, user);
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

    public void checkIfOrderAmountIsSufficent(List<ProductResponseDto> products)
            throws OrderException {

        double minOrderAmount = 50.0;

        double total = products.stream()
                .map(ProductResponseDto::price)
                .reduce(0.0, Double::sum);

        if (minOrderAmount > total)
            throw new OrderException("The order amount of " + total + " is lower than the minimum order amount.");
    }

    public void checkIfOrderHasMoreThanThreeProductsInSameCategory(List<ProductResponseDto> products)
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

    public Optional<List<OrderResponseDto>> findAll() {

        List<OrderResponseDto> orderResponseDtos =
                orderRepository.findAll().stream().map(mapper::orderToOrderResponseDto).toList();

        return Optional.of(orderResponseDtos);
    }

    public void delete(long orderId) {
        orderRepository.deleteById(orderId);
    }
}
