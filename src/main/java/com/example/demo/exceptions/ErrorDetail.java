package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor

public class ErrorDetail {
    private String message;
    private Date timestamp;
    private String details;
}
