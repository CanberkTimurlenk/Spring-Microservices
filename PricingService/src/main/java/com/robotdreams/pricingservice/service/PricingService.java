package com.robotdreams.pricingservice.service;

import com.robotdreams.pricingservice.dto.pricecontainer.request.PriceContainerRequestDto;
import com.robotdreams.pricingservice.dto.pricecontainer.response.PriceContainerResponseDto;
import com.robotdreams.pricingservice.entity.PriceContainer;
import com.robotdreams.pricingservice.entity.ContainerItem;
import com.robotdreams.usergrpcservice.UserResponse;
import com.robotdreams.pricingservice.grpc.UserGrpcClientService;
import com.robotdreams.pricingservice.service.mapper.PriceContainerMapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PricingService {

    private final UserGrpcClientService userGrpcClientService;
    private final PriceContainerMapper priceContainerMapper;

    public PriceContainerResponseDto getPricedCart(PriceContainerRequestDto priceContainerRequestDto) {

        UserResponse userResponse = userGrpcClientService.getUserResponseDto(priceContainerRequestDto.userId());

        PriceContainer priceContainer = getCart(priceContainerRequestDto, userResponse);

        return priceContainerMapper.toPriceContainerResponseDto(priceContainer);
    }

    public PriceContainer getCart(PriceContainerRequestDto priceContainerRequestDto, UserResponse userResponse) {
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
                .isPremiumCart(userResponse.getPremium())
                .discountCode(priceContainerRequestDto.discountCode())
                .build();
    }
}