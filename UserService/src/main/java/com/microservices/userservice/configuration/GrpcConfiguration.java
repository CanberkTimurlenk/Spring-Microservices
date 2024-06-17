package com.microservices.userservice.configuration;

import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class GrpcConfiguration {

    @Bean
    public GrpcServerConfigurer keepAliveServerConfigurer() {

        return serverBuilder -> {
            if (serverBuilder instanceof NettyServerBuilder nettyServerBuilder) {

                nettyServerBuilder
                        .keepAliveTime(30, TimeUnit.SECONDS)
                        .keepAliveTimeout(5, TimeUnit.SECONDS).permitKeepAliveWithoutCalls(true)
                        .build();
            }
        };
    }
}
