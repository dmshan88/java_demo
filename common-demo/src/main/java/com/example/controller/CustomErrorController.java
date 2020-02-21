package com.example.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.common.CustomResponse;
import com.example.common.ErrorCode;

@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";
    
    @Autowired
    private ErrorAttributes errorAttributes;
    
    @Override
    public String getErrorPath() {
        return PATH;
    }
    
    @RequestMapping(value = PATH)
    CustomResponse<Object> error(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String, Object> errorMap = this.errorAttributes.getErrorAttributes(requestAttributes, false);
        System.out.println(errorMap);
        Integer status = (Integer)errorMap.get("status");
        String message = errorMap.get("error").toString() + ":" + errorMap.get("message").toString();
        return CustomResponse.error(ErrorCode.UNKNOWN.getCode(), message);
    }
    

}
