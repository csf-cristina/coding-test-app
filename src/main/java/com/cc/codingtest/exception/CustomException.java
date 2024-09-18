package com.cc.codingtest.exception;

public class CustomException extends RuntimeException {
    private String details;

    public CustomException(String message, String details) {
        super(message);
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}