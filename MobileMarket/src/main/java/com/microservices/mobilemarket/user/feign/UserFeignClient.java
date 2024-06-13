package com.microservices.mobilemarket.user.feign;

import com.microservices.mobilemarket.user.dto.UserCredentials;
import com.microservices.mobilemarket.user.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "UserService")
public interface UserFeignClient {

    @PostMapping("/users/findByCredentials")
    UserResponseDto findUserByCredentials(@RequestBody UserCredentials credentials);

}
