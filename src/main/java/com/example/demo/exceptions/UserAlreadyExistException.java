package com.example.demo.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistException extends ApiException {


    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}