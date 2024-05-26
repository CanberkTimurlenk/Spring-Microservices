package com.microservices.basketservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "UserService")
public interface UserFeignClient {

    @GetMapping("/users/userValidity")
    public ResponseEntity<Boolean> checkIfUserIsValid(long userId);
}
