package com.mindhub.user_service.exeptions;

public class UserNotFoundExceptionAuth extends RuntimeException{
    public UserNotFoundExceptionAuth(String message) {
        super(message);
    }
}
