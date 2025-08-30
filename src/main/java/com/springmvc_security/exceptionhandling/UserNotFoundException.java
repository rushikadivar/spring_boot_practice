package com.springmvc_security.exceptionhandling;

public class UserNotFoundException extends RuntimeException  implements UserException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
