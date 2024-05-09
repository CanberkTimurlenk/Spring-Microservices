package com.robotdreams.basketservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ProductService")
public class ProductFeignClient {



}
