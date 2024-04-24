package com.robotdreams.pricingservice.unittest;

import com.robotdreams.pricingservice.dto.pricecontainer.request.ContainerItemRequestDto;
import com.robotdreams.pricingservice.dto.pricecontainer.request.PriceContainerRequestDto;
import com.robotdreams.pricingservice.entity.PriceContainer;
import com.robotdreams.pricingservice.service.PricingService;
import com.robotdreams.usergrpcservice.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import java.math.BigDecimal;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class PricingServiceUnitTests {

    @InjectMocks
    private PricingService pricingService;

    @Test
    public void testCheckCartTotalPriceWithExpectedValueAndNonPremiumUser() {

        ContainerItemRequestDto item1 = Mockito.mock(ContainerItemRequestDto.class);
        ContainerItemRequestDto item2 = Mockito.mock(ContainerItemRequestDto.class);
        ContainerItemRequestDto item3 = Mockito.mock(ContainerItemRequestDto.class);

        // item1
        Mockito.when(item1.productId()).thenReturn(1L);
        Mockito.when(item1.quantity()).thenReturn(2);
        Mockito.when(item1.unitPrice()).thenReturn(BigDecimal.valueOf(10.0));
        Mockito.when(item1.discountCode()).thenReturn("DISCOUNT5");

        // item2
        Mockito.when(item2.productId()).thenReturn(2L);
        Mockito.when(item2.quantity()).thenReturn(1);
        Mockito.when(item2.unitPrice()).thenReturn(BigDecimal.valueOf(15.0));
        Mockito.when(item2.discountCode()).thenReturn("NO_DISCOUNT");

        // item3
        Mockito.when(item3.productId()).thenReturn(3L);
        Mockito.when(item3.quantity()).thenReturn(3);
        Mockito.when(item3.unitPrice()).thenReturn(BigDecimal.valueOf(8.0));
        Mockito.when(item3.discountCode()).thenReturn("DISCOUNT10");

        List<ContainerItemRequestDto> containerItems = Arrays.asList(item1, item2, item3);

        //cart
        PriceContainerRequestDto cart = Mockito.mock(PriceContainerRequestDto.class);

        //cart
        Mockito.when(cart.userId()).thenReturn(123456L);
        Mockito.when(cart.containerItems()).thenReturn(containerItems);
        Mockito.when(cart.discountCode()).thenReturn("NEWUSER10");

        //user
        UserResponse userResponse = Mockito.mock(UserResponse.class);

        // user
        Mockito.when(userResponse.getPremium()).thenReturn(false);

        PriceContainer result = pricingService.getCart(cart, userResponse);

        assertEquals(0, result.getCartTotalPrice().compareTo(new BigDecimal("59")));
    }

    @Test
    public void testCheckCartTotalPriceWithExpectedValueAndPremiumUser() {

        ContainerItemRequestDto item1 = Mockito.mock(ContainerItemRequestDto.class);
        ContainerItemRequestDto item2 = Mockito.mock(ContainerItemRequestDto.class);
        ContainerItemRequestDto item3 = Mockito.mock(ContainerItemRequestDto.class);

        // item1
        Mockito.when(item1.productId()).thenReturn(1L);
        Mockito.when(item1.quantity()).thenReturn(2);
        Mockito.when(item1.unitPrice()).thenReturn(BigDecimal.valueOf(10.0));
        Mockito.when(item1.discountCode()).thenReturn("DISCOUNT5");

        // item2
        Mockito.when(item2.productId()).thenReturn(2L);
        Mockito.when(item2.quantity()).thenReturn(1);
        Mockito.when(item2.unitPrice()).thenReturn(BigDecimal.valueOf(15.0));
        Mockito.when(item2.discountCode()).thenReturn("NO_DISCOUNT");

        // item3
        Mockito.when(item3.productId()).thenReturn(3L);
        Mockito.when(item3.quantity()).thenReturn(3);
        Mockito.when(item3.unitPrice()).thenReturn(BigDecimal.valueOf(8.0));
        Mockito.when(item3.discountCode()).thenReturn("DISCOUNT10");

        List<ContainerItemRequestDto> containerItems = Arrays.asList(item1, item2, item3);

        //cart
        PriceContainerRequestDto cart = Mockito.mock(PriceContainerRequestDto.class);

        //cart
        Mockito.when(cart.userId()).thenReturn(123456L);
        Mockito.when(cart.containerItems()).thenReturn(containerItems);
        Mockito.when(cart.discountCode()).thenReturn("NEWUSER10");

        //user
        UserResponse userResponse = Mockito.mock(UserResponse.class);

        // user
        Mockito.when(userResponse.getPremium()).thenReturn(true);

        PriceContainer result = pricingService.getCart(cart, userResponse);

        assertEquals(0, result.getCartTotalPrice().compareTo(new BigDecimal("56.05")));
    }
}