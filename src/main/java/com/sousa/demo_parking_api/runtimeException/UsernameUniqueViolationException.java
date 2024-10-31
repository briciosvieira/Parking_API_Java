package com.sousa.demo_parking_api.runtimeException;

public class UsernameUniqueViolationException extends RuntimeException {
    public UsernameUniqueViolationException(String message) {
        super(message);
    }
}
