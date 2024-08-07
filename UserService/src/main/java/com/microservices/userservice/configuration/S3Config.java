package com.microservices.userservice.configuration;

import lombok.RequiredArgsConstructor;
import org.common.sns.properties.AwsS3BucketProperties;
import org.common.sns.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class S3Config {

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Bean
    public StorageService storageService( ) {
        return new StorageService(new AwsS3BucketProperties(bucketName));
    }

}
