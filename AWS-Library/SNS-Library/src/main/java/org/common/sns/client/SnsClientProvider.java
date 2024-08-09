package org.common.sns.client;



import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

public class SnsClientProvider {

    private final Configuration configuration;

    public SnsClientProvider() {
        try {
            configuration = new Configurations().properties("config.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public SnsClient createSnsClient() {

        String region = configuration.getString("sns.region");

        return SnsClient.builder()
                .region(Region.of(region))
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }
}