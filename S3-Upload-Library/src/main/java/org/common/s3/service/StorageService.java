package org.common.s3.service;

import io.awspring.cloud.s3.S3Resource;
import org.common.s3.FileStrategy;
import org.common.s3.properties.AwsS3BucketProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import io.awspring.cloud.s3.S3Template;

@EnableConfigurationProperties(AwsS3BucketProperties.class)
public class StorageService {

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    private final S3Template s3Template;
    private final AwsS3BucketProperties awsS3BucketProperties;

    public StorageService(S3Template s3Template, AwsS3BucketProperties awsS3BucketProperties) {
        this.s3Template = s3Template;
        this.awsS3BucketProperties = awsS3BucketProperties;
    }

    public void save(FileStrategy file) {
            var objectKey = file.getName();
            var bucketName = awsS3BucketProperties.getBucketName();
            s3Template.upload(bucketName, objectKey, file.getInputStream());
    }

    public void save(FileStrategy file,String objectKey) {
        var bucketName = awsS3BucketProperties.getBucketName();
        s3Template.upload(bucketName, objectKey, file.getInputStream());
    }

    public S3Resource retrieve(String objectKey) {
        var bucketName = awsS3BucketProperties.getBucketName();
        return s3Template.download(bucketName, objectKey);
    }

    public void delete(String objectKey) {
        var bucketName = awsS3BucketProperties.getBucketName();
        s3Template.deleteObject(bucketName, objectKey);
    }

}