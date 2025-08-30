package com.springmvc_security.exceptionhandling.password;

public class WeakPasswordException extends RuntimeException implements PasswordException {
    public WeakPasswordException(String message) {
        super(message);
    }
}
