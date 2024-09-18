package com.cc.codingtest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.net.UnknownHostException;

@RestControllerAdvice
public class ExceptionHandlerConfig {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerConfig.class);

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDetails> handleCustomException(CustomException ex) {
        logger.error("Custom exception: {}", ex.getDetails());
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), ex.getDetails());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<ErrorDetails> handleUnknownHostException(UnknownHostException ex) {
        String errorMessage = "Failed to resolve the URL. Please check the configured API endpoint.";
        logger.error("DNS resolution error: {}", ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails("DNS Error", errorMessage);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_GATEWAY);
    }

    // Handle general uncaught exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGeneralException(Exception ex) {
        logger.error("Unexpected exception: {}", ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails("Unexpected error occurred", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle I/O errors
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDetails> handleIOException(IOException ex) {
        String errorMessage = "I/O error occurred while fetching data. Please check the URL or network connection.";
        logger.error("I/O error: {}", ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails("I/O Error", errorMessage);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_GATEWAY);
    }
}
