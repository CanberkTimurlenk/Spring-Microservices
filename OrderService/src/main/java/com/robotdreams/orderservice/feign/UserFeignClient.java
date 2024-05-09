package com.robotdreams.orderservice.feign;

import com.robotdreams.orderservice.dto.response.external.UserInfoResponseDto;
import com.robotdreams.orderservice.exceptionHandling.UserException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@FeignClient(name = "userService")
public interface UserFeignClient {

    @GetMapping("/users/{userId}")
    @CircuitBreaker(name = "getInfoCBreaker", fallbackMethod = "getInfoServiceFallback")
    UserInfoResponseDto getInfo(@PathVariable long userId);

    default UserInfoResponseDto getInfoServiceFallback(Long userId, Exception e) {
        throw new UserException("An error occurred while retrieving user information." + userId);
    }
}