package com.example.demo.exceptions;

public class NonUniqueResultException extends ApiException {
    public NonUniqueResultException(String message)
    {
        super(message);
    }

    public NonUniqueResultException(String message, Object data) {
        super(message, data);
    }
}
