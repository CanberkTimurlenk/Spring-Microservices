package com.microservices.orderservice.configuration;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableFeignClients
@EnableDiscoveryClient
@EnableKafka
public class GeneralConfiguration {
}
