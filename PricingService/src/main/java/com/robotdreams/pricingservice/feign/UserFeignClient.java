package com.robotdreams.pricingservice.feign;

import com.robotdreams.pricingservice.dto.user.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UserService")
public interface UserFeignClient {

    @GetMapping("/users/{id}")
    UserResponseDto findUserById(@PathVariable long userId);

}
