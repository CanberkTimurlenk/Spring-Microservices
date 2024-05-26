package com.microservices.pricingservice.feign;

import com.microservices.pricingservice.dto.user.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UserService")
public interface UserFeignClient {

    @GetMapping("/users/{userId}")
    UserResponseDto findUserById(@PathVariable long userId);

}
