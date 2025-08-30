package com.springmvc_security.constraints.userrequestconstraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AtLeastOneFieldValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneField {
    String message() default "Either name or email must be provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
