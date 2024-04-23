package com.robotdreams.pricingservice.service;

import com.robotdreams.pricingservice.dto.pricecontainer.request.CartContainerRequestDto;
import com.robotdreams.pricingservice.dto.pricecontainer.response.PriceContainerResponseDto;
import com.robotdreams.pricingservice.dto.user.UserResponseDto;
import com.robotdreams.pricingservice.entity.PriceContainer;
import com.robotdreams.pricingservice.entity.ContainerItem;
import com.robotdreams.usergrpcservice.UserResponse;
import com.robotdreams.pricingservice.grpc.UserGrpcClientService;
import com.robotdreams.pricingservice.service.mapper.PriceContainerMapper;


import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PricingService {

    private final UserGrpcClientService userGrpcClientService;
    private final PriceContainerMapper priceContainerMapper;

    public PriceContainerResponseDto getPricedCart(CartContainerRequestDto pricedCardRequestDto) {

        UserResponse userResponse = userGrpcClientService.getUserResponseDto(pricedCardRequestDto.userId());

        PriceContainer priceContainer = getCart(pricedCardRequestDto, userResponse);

        return priceContainerMapper.toPriceContainerResponseDto(priceContainer);
    }

    public PriceContainer getCart(CartContainerRequestDto pricedCardRequestDto, UserResponse userResponse) {
        List<ContainerItem> priceContainerItems = pricedCardRequestDto.containerItems().stream()
                .map(priceContainerItem -> ContainerItem.builder()
                        .productId(priceContainerItem.productId())
                        .unitPrice(priceContainerItem.unitPrice())
                        .discountCode(priceContainerItem.discountCode())
                        .quantity(priceContainerItem.quantity())
                        .build())
                .toList();

        return PriceContainer.builder()
                .userId(pricedCardRequestDto.userId())
                .containerItems(priceContainerItems)
                .isPremiumCart(userResponse.getPremium())
                .discountCode(pricedCardRequestDto.discountCode())
                .build();
    }
}