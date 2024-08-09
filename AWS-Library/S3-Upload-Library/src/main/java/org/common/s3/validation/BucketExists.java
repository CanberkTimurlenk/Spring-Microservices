package org.common.s3.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.*;
import jakarta.validation.Payload;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BucketExistenceValidator.class)
public @interface BucketExists {

    String message() default "No bucket exists with configured name.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}