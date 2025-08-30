package com.springmvc_security.exceptionhandling;

import com.springmvc_security.exceptionhandling.password.WeakPasswordException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<?> handleUserAlreadyExist(final UserAlreadyExist ex) {
        ApiExceptionTemplate apiException = new ApiExceptionTemplate(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(final UserNotFoundException ex) {
        ApiExceptionTemplate apiException = new ApiExceptionTemplate(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidMethodArgumentException(final MethodArgumentNotValidException ex) {
        ApiExceptionTemplate apiException = new ApiExceptionTemplate(HttpStatus.BAD_REQUEST,
                ex.getBindingResult().getFieldErrors().stream()
                        .map(err -> err.getField() + ":" + err.getDefaultMessage())
                        .toList()
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
        ApiExceptionTemplate apiException = new ApiExceptionTemplate(HttpStatus.BAD_REQUEST, "Validation error: " + ex.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WeakPasswordException.class)
    public ResponseEntity<?> handleWeakPasswordException(final WeakPasswordException ex) {
        ApiExceptionTemplate apiException = new ApiExceptionTemplate(HttpStatus.BAD_REQUEST, "Illegal argument: " + ex.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
