package com.example.demo.exceptions;

import com.example.demo.exceptions.ApiException;

public class InternalServerErrorException extends ApiException {
    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Object data) {
        super(message, data);
    }
}
