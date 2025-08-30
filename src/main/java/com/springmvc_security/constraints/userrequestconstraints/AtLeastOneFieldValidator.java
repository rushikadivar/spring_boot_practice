package com.springmvc_security.constraints.userrequestconstraints;

import com.springmvc_security.dto.UserSearchRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class AtLeastOneFieldValidator implements ConstraintValidator<AtLeastOneField, UserSearchRequest> {
    @Override
    public boolean isValid(UserSearchRequest userSearchRequest, ConstraintValidatorContext constraintValidatorContext) {
        return userSearchRequest != null && (
                (
                        Objects.nonNull(userSearchRequest.getUsername()) && !userSearchRequest.getUsername().isEmpty()) ||
                        Objects.nonNull(userSearchRequest.getEmail()) && !userSearchRequest.getEmail().isEmpty()
                );
    }
}
