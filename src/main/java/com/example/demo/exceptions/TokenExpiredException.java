package com.example.demo.exceptions;



public class TokenExpiredException extends ApiException {

    public TokenExpiredException() {
        super();
    }

    public TokenExpiredException(String msg) {
        super(msg);
    }

}
