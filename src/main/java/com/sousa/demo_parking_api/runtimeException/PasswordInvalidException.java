package com.sousa.demo_parking_api.runtimeException;

public class PasswordInvalidException extends RuntimeException{
    public PasswordInvalidException(String message) {
        super(message);
    }

}
