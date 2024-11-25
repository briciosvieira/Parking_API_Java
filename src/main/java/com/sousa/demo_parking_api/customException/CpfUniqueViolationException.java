package com.sousa.demo_parking_api.customException;

public class CpfUniqueViolationException extends Throwable {
    public CpfUniqueViolationException(String message) {
        super(message);
    }
}
