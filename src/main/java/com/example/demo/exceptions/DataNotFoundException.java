package com.example.demo.exceptions;

public class DataNotFoundException extends ApiException {



    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message) {
        super(message);
    }

}