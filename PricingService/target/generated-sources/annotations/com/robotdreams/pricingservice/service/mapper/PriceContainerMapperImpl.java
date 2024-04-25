package com.robotdreams.pricingservice.service.mapper;

import com.robotdreams.pricingservice.dto.pricecontainer.response.ContainerItemResponseDto;
import com.robotdreams.pricingservice.dto.pricecontainer.response.PriceContainerResponseDto;
import com.robotdreams.pricingservice.entity.PriceContainer;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-25T12:13:11+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class PriceContainerMapperImpl implements PriceContainerMapper {

    @Override
    public PriceContainerResponseDto toPriceContainerResponseDto(PriceContainer cart) {
        if ( cart == null ) {
            return null;
        }

        List<ContainerItemResponseDto> containerItems = null;
        long userId = 0L;
        BigDecimal cartTotalPrice = null;

        containerItems = mapToContainerItemResponseDto( cart.getContainerItems() );
        userId = cart.getUserId();
        cartTotalPrice = cart.getCartTotalPrice();

        boolean isPremiumCart = false;

        PriceContainerResponseDto priceContainerResponseDto = new PriceContainerResponseDto( userId, containerItems, cartTotalPrice, isPremiumCart );

        return priceContainerResponseDto;
    }
}
