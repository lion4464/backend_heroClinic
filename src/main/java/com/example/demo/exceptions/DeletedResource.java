package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor

public class DeletedResource {
    private String message;
    private Date timestamp;
}
