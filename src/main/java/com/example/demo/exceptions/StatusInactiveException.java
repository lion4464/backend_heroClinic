package com.example.demo.exceptions;

public class StatusInactiveException  extends  ApiException{
    public StatusInactiveException() {
        super();
    }

    public StatusInactiveException(String message) {
        super(message);
    }
}
