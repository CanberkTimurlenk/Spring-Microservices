package com.robotdreams.pricingservice.service;

import com.robotdreams.pricingservice.dto.ProductPriceRequestDto;
import com.robotdreams.pricingservice.feign.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PricingService {

    private final UserFeignClient userFeignClient;

    public void getPrice(PricedCardRequestDto pricedCardRequestDto) {


    }

    public void getProductPrice(ProductPriceRequestDto productPriceRequestDto)
    {

    }

}
