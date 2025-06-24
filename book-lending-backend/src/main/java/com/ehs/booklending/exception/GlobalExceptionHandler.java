package com.ehs.booklending.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * GlobalExceptionHandler is a centralized exception handling class for handling
 * different types of exceptions across the application. This class ensures meaningful
 * error responses are returned for various failure scenarios.
 *
 * The error response contains:
 * - A timestamp indicating when the error occurred
 * - A descriptive error code
 * - A detailed error message
 *
 * It uses the @RestControllerAdvice annotation to intercept exceptions globally
 * for all controllers and map them to appropriate HTTP status codes and response bodies.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error("NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error("BAD_REQUEST", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error("INTERNAL_ERROR", "Something went wrong"));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleGeneric(NoHandlerFoundException noHandlerFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error("NOT_FOUND", "No resource found"));
    }


    private Map<String, Object> error(String code, String message) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "error", code,
                "message", message
        );
    }
}

