package com.microservices.productservice.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Configuration
@EnableDiscoveryClient
@EnableCaching
public class GeneralConfiguration {
}
