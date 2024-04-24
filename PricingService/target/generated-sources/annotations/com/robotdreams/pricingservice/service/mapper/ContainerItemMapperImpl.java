package com.robotdreams.pricingservice.service.mapper;

import com.robotdreams.pricingservice.dto.pricecontainer.response.ContainerItemResponseDto;
import com.robotdreams.pricingservice.entity.ContainerItem;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-24T00:52:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class ContainerItemMapperImpl implements ContainerItemMapper {

    @Override
    public ContainerItemResponseDto toContainerItemResponseDto(ContainerItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        long productId = 0L;
        int quantity = 0;
        BigDecimal itemTotalPrice = null;
        BigDecimal unitPrice = null;

        productId = cartItem.getProductId();
        quantity = cartItem.getQuantity();
        itemTotalPrice = cartItem.getItemTotalPrice();
        unitPrice = cartItem.getUnitPrice();

        ContainerItemResponseDto containerItemResponseDto = new ContainerItemResponseDto( productId, quantity, itemTotalPrice, unitPrice );

        return containerItemResponseDto;
    }
}
