package com.example.compoment;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.common.CustomException;
import com.example.common.CustomResponse;
import com.example.common.ErrorCode;

import lombok.extern.log4j.Log4j;

@Log4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public CustomResponse<Object> error(CustomException exception, HttpServletResponse response) {
        log.error("CustomException", exception);
        return CustomResponse.error(exception.getCode(), exception.getMessage());
    }
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public CustomResponse<Object> error(NoHandlerFoundException exception, HttpServletResponse response) {
        log.error("404:" + exception.getMessage());
        return CustomResponse.error(ErrorCode.ERROR.getCode(), "404");
    }
    
    @ExceptionHandler(DataAccessException.class)
    public CustomResponse<Object> error(DataAccessException exception, HttpServletResponse response) {
        log.error("DataAccessException", exception);
        return CustomResponse.error(ErrorCode.DATABASE_ERROR);
    }
    
    @ExceptionHandler(Exception.class)
    public CustomResponse<Object> error(Exception exception, HttpServletResponse response) {
        log.error("Exception", exception);
        return CustomResponse.error(ErrorCode.EXCEPTION);
    }
}
