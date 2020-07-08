package com.example.exception;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.common.CustomException;
import com.example.common.CustomResponse;
import com.example.common.ErrorCode;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice()
public class ApiExceptionHandler {
    
    @ExceptionHandler(UnauthorizedException.class)
    public CustomResponse<Object> error(UnauthorizedException exception, HttpServletResponse response) {
        return CustomResponse.error(ErrorCode.NO_PERMISSION);
    }
    
    @ExceptionHandler(CustomException.class)
    public CustomResponse<Object> error(CustomException exception, HttpServletResponse response) {
        return CustomResponse.error(exception.getCode(), exception.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    public CustomResponse<Object> error(Exception exception, HttpServletResponse response) {
        log.error(exception);
        return CustomResponse.error(ErrorCode.ERROR.getCode(), exception.getMessage());
    }

}