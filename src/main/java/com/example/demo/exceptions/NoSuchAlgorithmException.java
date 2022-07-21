package com.example.demo.exceptions;

public class NoSuchAlgorithmException extends ApiException{
    public NoSuchAlgorithmException() {
        super();
    }

    public NoSuchAlgorithmException(String message, Object data) {
        super(message, data);
    }

    public NoSuchAlgorithmException(String message) {
        super(message);
    }
}
