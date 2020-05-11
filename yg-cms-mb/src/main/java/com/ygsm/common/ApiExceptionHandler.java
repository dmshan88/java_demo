package com.ygsm.common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Order(value = Ordered.LOWEST_PRECEDENCE)
@RestControllerAdvice
public class ApiExceptionHandler {
    
    protected final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

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
    
    @ExceptionHandler(ServletRequestBindingException.class)
    public CustomResponse<Object> error(ServletRequestBindingException exception, HttpServletResponse response) {
        log.error("ServletRequestBindingException" + exception.getMessage());
        return CustomResponse.error(ErrorCode.PARAM_ERROR);
    }
    
    @ExceptionHandler(ServletException.class)
    public CustomResponse<Object> error(ServletException exception, HttpServletResponse response) {
        log.error("ServletException" + exception.getMessage());
        return CustomResponse.error(ErrorCode.ERROR.getCode(), "请求错误");
    }
    
    @ExceptionHandler(Exception.class)
    public CustomResponse<Object> error(Exception exception, HttpServletResponse response) {
        log.error("Exception", exception);
        return CustomResponse.error(ErrorCode.EXCEPTION);
    }
}
