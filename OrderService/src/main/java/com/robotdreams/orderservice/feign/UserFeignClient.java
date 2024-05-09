package com.robotdreams.orderservice.feign;

import com.robotdreams.orderservice.dto.response.external.UserInfoResponseDto;
import com.robotdreams.orderservice.exceptionHandling.UserException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userService")
public interface UserFeignClient {

    @GetMapping("/users/{userId}")
    @CircuitBreaker(name = "getUserInfoCBreaker", fallbackMethod = "getUserInfoServiceFallback")
    UserInfoResponseDto getInfo(@PathVariable Long userId);

    default UserInfoResponseDto getUserInfoServiceFallback(Long userId, Exception e) {
        // Execute If UserService Is Not Accessible
        throw new UserException("An error occurred while retrieving user information." + userId);
    }
}