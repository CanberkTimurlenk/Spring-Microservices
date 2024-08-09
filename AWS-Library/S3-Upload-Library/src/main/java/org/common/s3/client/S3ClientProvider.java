package org.common.s3.client;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import io.awspring.cloud.s3.S3Template;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class S3ClientProvider {

    private final Configuration configuration;

    public S3ClientProvider() {
        try {
            configuration = new Configurations().properties("config.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }


    public S3Template createS3Template() {
        String endpoint = configuration.getString("s3.endpoint");
        String region = configuration.getString("s3.region");

        return S3Client.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }
}