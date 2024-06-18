package org.common.s3.validation;

import io.awspring.cloud.s3.S3Template;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BucketExistenceValidator implements ConstraintValidator<BucketExists, String> {

    public BucketExistenceValidator(S3Template s3Template) {
        this.s3Template = s3Template;
    }

    private final S3Template s3Template;

    @Override
    public boolean isValid(String bucketName, ConstraintValidatorContext context) {
        return s3Template.bucketExists(bucketName);
    }
}
