package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiException extends RuntimeException{
    private  String message;
    private  HttpStatus httpStatus;
    private  ZonedDateTime timestamp;

    public ApiException(String message) {
        this.message = message;
    }

    public ApiException(String message, Object data) {
        this.message = message;
        setHttpStatus(HttpStatus.BAD_REQUEST);
    }
}
