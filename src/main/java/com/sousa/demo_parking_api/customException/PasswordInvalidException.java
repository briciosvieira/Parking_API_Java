package com.sousa.demo_parking_api.customException;

public class PasswordInvalidException extends RuntimeException{
    public PasswordInvalidException(String message) {
        super(message);
    }

}
