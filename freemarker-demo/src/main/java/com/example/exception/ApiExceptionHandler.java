package com.example.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.common.CustomException;
import com.example.common.CustomResponse;
import com.example.common.ErrorCode;

@RestControllerAdvice()
public class ApiExceptionHandler {
    
    @ExceptionHandler(CustomException.class)
    public CustomResponse<Object> error(CustomException exception, HttpServletResponse response) {
        return CustomResponse.error(exception.getCode(), exception.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    public CustomResponse<Object> error(Exception exception, HttpServletResponse response) {
        return CustomResponse.error(ErrorCode.ERROR.getCode(), exception.getMessage());
    }

}