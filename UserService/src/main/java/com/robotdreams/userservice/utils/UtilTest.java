package com.robotdreams.userservice.utils;

import com.robotdreams.userservice.service.UserService;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class UtilTest {


    private final UserService userService;


//  @PostConstruct
//    public void saveTestData() {

//        // create sample user data
//        UserRequestDto userRequestDto = new UserRequestDto();
//        userRequestDto.setFirstname("John");
//        userRequestDto.setLastname("Doe");
//        userRequestDto.setEmail("john.doe@example.com");
//        userRequestDto.setPhoneNumber("+1234567890");
//        userRequestDto.setAddress("123 Main Street, Cityville, ST 12345");
//
//        // save sample user date
//        userService.save(userRequestDto);
//
//
//        // create sample product request dto
//        ProductRequestDto productRequestDto = new ProductRequestDto();
//        productRequestDto.setName("Example Product");
//        productRequestDto.setCategory("Electronics");
//        productRequestDto.setPhotoUrl("https://example.com/product_photo.jpg");
//        productRequestDto.setDescription("This is an example product description.");
//        productRequestDto.setPrice(99.99);
//
//        // create sample product detail request dto
//        ProductDetailRequestDto productDetailRequestDto = new ProductDetailRequestDto();
//        productDetailRequestDto.setProductInfo("test");
//        productDetailRequestDto.setProductSerialNumber("merhaba");
//
//        // set product detail of product request dto
//        productRequestDto.setProductDetail(productDetailRequestDto);
//
//        // save product
//        productService.create(productRequestDto);
//
//        // set order
//        OrderRequestDto orderRequestDto = new OrderRequestDto();
//        orderRequestDto.setProductIdList(new ArrayList<>(Arrays.asList(1l)));
//        orderRequestDto.setOrderNumber(UUID.randomUUID().toString());
//        orderRequestDto.setDescription("deneme");
//        orderRequestDto.setUserId(1l);
//
//        // save order
//        orderService.save(orderRequestDto);

//    }

}
