package com.springmvc_security.exceptionhandling;

import java.io.Serializable;

public class UserAlreadyExist extends RuntimeException implements Serializable, UserException {
    public UserAlreadyExist(String message) {
        super(message);
    }
}
