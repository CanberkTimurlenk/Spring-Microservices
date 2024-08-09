package org.common.s3.properties;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public class AwsS3BucketProperties {

    public AwsS3BucketProperties(String bucketName) {
        this.bucketName = bucketName;
    }

    @NotBlank(message = "S3 bucket name must be configured")
    private final String bucketName;

    public @NotBlank(message = "S3 bucket name must be configured") String getBucketName() {
        return bucketName;
    }
}
