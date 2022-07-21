package com.example.demo.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.validation.UnexpectedTypeException;
import java.nio.file.AccessDeniedException;
import java.rmi.UnexpectedException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(value = {ResourceAlreadyExistsException.class})
    public ResponseEntity<Object> exception(ResourceAlreadyExistsException e){
        HttpStatus httpBody=HttpStatus.TOO_EARLY;
        ApiException result=new ApiException(
                e.getMessage(),
                httpBody,


                ZonedDateTime.now(ZoneId.of("Z"))
        );



    return new ResponseEntity<>(result,httpBody);
    }

    @ExceptionHandler(value = {NonUniqueResultException.class})
    public ResponseEntity<Object> exeptionNonUniqueResult(NonUniqueResultException e)
    {
        UniqueException result=new UniqueException(
                e.getMessage(),
               new Date());
        return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {NoSuchAlgorithmException.class})
    public ResponseEntity<Object> exceptionNoSuchAlgorithmException(NoSuchAlgorithmException e){
        HttpStatus httpBody=HttpStatus.BAD_REQUEST;
        ApiException result=new ApiException(
                e.getMessage(),
                httpBody,


                ZonedDateTime.now(ZoneId.of("Z"))
        );
    return new ResponseEntity<>(result,httpBody);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        ErrorDetail errorDetail=new ErrorDetail(
                "validation Error", new Date(),
                ex.getBindingResult().getFieldError().getDefaultMessage()
        );

        return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            ConstraintViolationException ex) {
        UniqueException errorDetail=new UniqueException(
               ex.getMessage(),
                new Date());
    return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(StatusInactiveException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            StatusInactiveException ex) {
        UniqueException errorDetail=new UniqueException(
               ex.getMessage(),
                new Date());
    return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundExceptions(
            DataNotFoundException ex) {
        UniqueException errorDetail=new UniqueException(
               ex.getMessage(),
                new Date());
    return new ResponseEntity<>(errorDetail,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            EmptyResultDataAccessException ex) {
        UniqueException errorDetail=new UniqueException(
             "This Object is removed before))",
                new Date());
    return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            IllegalArgumentException ex) {
        UniqueException errorDetail=new UniqueException(
             ex.getMessage(),
                new Date());
    return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
    }
@ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleValidationExceptions(
        AccessDeniedException ex) {
        UniqueException errorDetail=new UniqueException(
             ex.getMessage(),
                new Date());
    return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<Object> handleUnexpectedTypeException(
            UnexpectedTypeException ex) {
        UniqueException errorDetail = new UniqueException(
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }



}
