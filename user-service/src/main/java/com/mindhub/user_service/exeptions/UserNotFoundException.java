package com.mindhub.user_service.exeptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("User with ID " + id + " not found");
    }
}
