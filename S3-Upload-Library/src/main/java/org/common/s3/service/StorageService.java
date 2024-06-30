package org.common.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import org.common.s3.FileStrategy;
import org.common.s3.properties.AwsS3BucketProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AwsS3BucketProperties.class)
public class StorageService {

    private final AmazonS3 s3Client;
    private final AwsS3BucketProperties awsS3BucketProperties;

    public StorageService(AwsS3BucketProperties awsS3BucketProperties) {

        this.awsS3BucketProperties = awsS3BucketProperties;
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion()
                .build();
    }

    public void save(FileStrategy file) {
        var objectKey = file.getName();
        var bucketName = awsS3BucketProperties.getBucketName();
        s3Client.putObject(bucketName, objectKey, file.getInputStream(), null);
    }

    public void save(FileStrategy file, String objectKey) {
        var bucketName = awsS3BucketProperties.getBucketName();
        s3Client.putObject(bucketName, objectKey, file.getInputStream(), null);
    }

    public S3Object retrieve(String objectKey) {
        var bucketName = awsS3BucketProperties.getBucketName();
        return s3Client.getObject(bucketName, objectKey);
    }

    public void delete(String objectKey) {
        var bucketName = awsS3BucketProperties.getBucketName();
        s3Client.deleteObject(bucketName, objectKey);
    }

}