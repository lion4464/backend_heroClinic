package com.example.demo.exceptions;

public class ResourceAlreadyExistsException extends ApiException{
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
