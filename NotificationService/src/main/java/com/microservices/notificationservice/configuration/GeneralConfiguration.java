package com.microservices.notificationservice.configuration;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.microservices.notificationservice")
public class GeneralConfiguration {



}