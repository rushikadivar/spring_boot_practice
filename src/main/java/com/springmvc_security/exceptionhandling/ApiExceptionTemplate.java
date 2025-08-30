package com.springmvc_security.exceptionhandling;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiExceptionTemplate {
    private final HttpStatus status;
    private final Object message;
    private final LocalDateTime timestamp;

    public ApiExceptionTemplate(HttpStatus status, Object message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}
