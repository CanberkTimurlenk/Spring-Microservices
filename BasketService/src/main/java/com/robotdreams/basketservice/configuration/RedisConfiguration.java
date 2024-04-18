package com.robotdreams.basketservice.configuration;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

    @Bean
    public RedisConnectionFactory connectionFactory() {

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        RedisProperties properties = redisProperties();

        configuration.setUsername(properties.getUsername());
        configuration.setDatabase(properties.getDatabase());
        configuration.setPort(properties.getPort());
        configuration.setPassword(properties.getPassword());

        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    @Primary
    public RedisProperties redisProperties() {
        return new RedisProperties();
    }
}