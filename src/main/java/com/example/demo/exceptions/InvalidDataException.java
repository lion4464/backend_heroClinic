package com.example.demo.exceptions;

import java.util.List;

public class InvalidDataException extends ApiException {

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Object data) {
        super(message, data);
    }
}
