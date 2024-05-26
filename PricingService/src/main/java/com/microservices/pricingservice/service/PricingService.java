package com.microservices.pricingservice.service;

import com.microservices.pricingservice.dto.pricecontainer.request.PriceContainerRequestDto;
import com.microservices.pricingservice.dto.pricecontainer.response.PriceContainerResponseDto;
import com.microservices.pricingservice.dto.user.UserResponseDto;
import com.microservices.pricingservice.entity.PriceContainer;
import com.microservices.pricingservice.entity.ContainerItem;
import com.microservices.pricingservice.feign.UserFeignClient;
import com.microservices.pricingservice.service.mapper.PriceContainerMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PricingService {

    private final UserFeignClient userFeignClient;
    private final PriceContainerMapper priceContainerMapper;

    public PriceContainerResponseDto getPricedCart(PriceContainerRequestDto priceContainerRequestDto) {

        UserResponseDto userResponseDto = userFeignClient.findUserById(priceContainerRequestDto.userId());

        PriceContainer priceContainer = getCart(priceContainerRequestDto, userResponseDto);

        return priceContainerMapper.toPriceContainerResponseDto(priceContainer);
    }

    public PriceContainer getCart(PriceContainerRequestDto priceContainerRequestDto, UserResponseDto userResponseDto) {
        List<ContainerItem> priceContainerItems = priceContainerRequestDto.containerItems().stream()
                .map(priceContainerItem -> ContainerItem.builder()
                        .productId(priceContainerItem.productId())
                        .unitPrice(priceContainerItem.unitPrice())
                        .discountCode(priceContainerItem.discountCode())
                        .quantity(priceContainerItem.quantity())
                        .build())
                .toList();

        return PriceContainer.builder()
                .userId(priceContainerRequestDto.userId())
                .containerItems(priceContainerItems)
                .isPremiumCart(userResponseDto.premium())
                .discountCode(priceContainerRequestDto.discountCode())
                .build();
    }
}