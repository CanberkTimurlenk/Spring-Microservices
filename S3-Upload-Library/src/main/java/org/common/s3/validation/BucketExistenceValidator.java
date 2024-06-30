package org.common.s3.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BucketExistenceValidator implements ConstraintValidator<BucketExists, String> {

    public BucketExistenceValidator(S3Template s3Template) {
        this.s3Template = s3Template;
    }

    private final S3Client s3Client;

    @Override
    public boolean isValid(String bucketName, ConstraintValidatorContext context) {
        return s3Template.bucketExists(bucketName);
    }
}
