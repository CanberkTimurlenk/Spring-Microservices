package com.microservices.mobilemarket.order.controller;

import com.microservices.mobilemarket.auth.grpc.UserGrpcClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final UserGrpcClient userGrpcClient;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void test(){




    }
}
