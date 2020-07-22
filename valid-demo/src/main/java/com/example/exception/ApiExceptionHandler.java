package com.example.exception;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public void error(ValidationException exception, HttpServletResponse response) {
        System.out.println(exception.getMessage());

    }
    
    @ExceptionHandler(BindException.class)
    public void error(BindException exception, HttpServletResponse response) {
        String errorMessage = "";
        List<FieldError> errors = exception.getFieldErrors();
        if (errors == null || errors.isEmpty()) {
            errorMessage = exception.getMessage();
        } else {
            for (FieldError error : errors) {
                errorMessage += String.format("%s:%s;", error.getField(), error.getDefaultMessage());
            }
        }
        System.out.println(errorMessage);

    }
}
