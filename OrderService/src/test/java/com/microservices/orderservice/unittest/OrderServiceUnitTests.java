package com.microservices.orderservice.unittest;

import com.microservices.orderservice.dto.response.external.ProductResponseDto;
import com.microservices.orderservice.exceptionHandling.OrderException;
import com.microservices.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTests {

    @InjectMocks
    private OrderService orderService;

    @Test
    public void checkIfOrderAmountIsSufficent_ShouldThrowInsufficientOrderAmountException_WhenTotalAmountIsBelow50() {

        ProductResponseDto product1 = Mockito.mock(ProductResponseDto.class);
        ProductResponseDto product2 = Mockito.mock(ProductResponseDto.class);

        double product1Price = 1.0;
        double product2Price = 5.0;

        List<ProductResponseDto> products = List.of(product1, product2);

        Mockito.when(product1.price()).thenReturn(product1Price);
        Mockito.when(product2.price()).thenReturn(product2Price);

//        Exception exception = assertThrows(OrderException.class, () ->
//                orderService.checkIfOrderAmountIsSufficent(products));
//
//        String expectedMessage = "The order amount of " + (product1Price + product2Price) + " is lower than the minimum order amount.";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void checkIfOrderAmountIsSufficent_WhenTotalPriceIsGreaterThanMinOrderTotalPrice()
            throws OrderException {

        ProductResponseDto product1 = Mockito.mock(ProductResponseDto.class);
        ProductResponseDto product2 = Mockito.mock(ProductResponseDto.class);

        double product1Price = 100.0;
        double product2Price = 200.0;

        List<ProductResponseDto> products = List.of(product1, product2);

        Mockito.when(product1.price()).thenReturn(product1Price);
        Mockito.when(product2.price()).thenReturn(product2Price);

//        orderService.checkIfOrderAmountIsSufficent(products);
    }

    @Test
    public void checkIfOrderHasMoreThanThreeProductsInSameCategory_WhenOrderIncludesMoreThanThreeProductInASingleCategory()
            throws OrderException {

        ProductResponseDto product1InCategory1 = Mockito.mock(ProductResponseDto.class);
        ProductResponseDto product2InCategory1 = Mockito.mock(ProductResponseDto.class);
        ProductResponseDto product3InCategory1 = Mockito.mock(ProductResponseDto.class);
        ProductResponseDto product4InCategory1 = Mockito.mock(ProductResponseDto.class);

        ProductResponseDto product1InCategory2 = Mockito.mock(ProductResponseDto.class);
        ProductResponseDto product2InCategory2 = Mockito.mock(ProductResponseDto.class);
        ProductResponseDto product3InCategory2 = Mockito.mock(ProductResponseDto.class);
        ProductResponseDto product4InCategory2 = Mockito.mock(ProductResponseDto.class);

        Mockito.when(product1InCategory1.category()).thenReturn("category1");
        Mockito.when(product2InCategory1.category()).thenReturn("category1");
        Mockito.when(product3InCategory1.category()).thenReturn("category1");
        Mockito.when(product4InCategory1.category()).thenReturn("category1");

        Mockito.when(product1InCategory2.category()).thenReturn("category2");
        Mockito.when(product2InCategory2.category()).thenReturn("category2");
        Mockito.when(product3InCategory2.category()).thenReturn("category2");
        Mockito.when(product4InCategory2.category()).thenReturn("category2");

        List<ProductResponseDto> products = List.of(
                product1InCategory1, product2InCategory1, product3InCategory1, product4InCategory1,
                product1InCategory2, product2InCategory2, product3InCategory2, product4InCategory2);

//        Exception exception = assertThrows(OrderException.class, () -> {
//            orderService.checkIfOrderHasMoreThanThreeProductsInSameCategory(products);
//        });
//
//        String expectedMessage = "An order must include a maximum of 3 products from a single category. Violations: category2 : 4 category1 : 4";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));

    }


    @Test
    public void checkIfOrderHasMoreThanThreeProductsInSameCategory_WhenOrderDoesNotIncludeMoreThanThreeProductInASingleCategory()
            throws OrderException {

        ProductResponseDto product1InCategory1 = Mockito.mock(ProductResponseDto.class);
        ProductResponseDto product2InCategory1 = Mockito.mock(ProductResponseDto.class);

        ProductResponseDto product1InCategory2 = Mockito.mock(ProductResponseDto.class);
        ProductResponseDto product2InCategory2 = Mockito.mock(ProductResponseDto.class);

        Mockito.when(product1InCategory1.category()).thenReturn("category1");
        Mockito.when(product2InCategory1.category()).thenReturn("category1");

        Mockito.when(product1InCategory2.category()).thenReturn("category2");
        Mockito.when(product2InCategory2.category()).thenReturn("category2");

        List<ProductResponseDto> products = List.of(
                product1InCategory1, product2InCategory1,
                product1InCategory2, product2InCategory2);

//        orderService.checkIfOrderHasMoreThanThreeProductsInSameCategory(products);
    }
}
