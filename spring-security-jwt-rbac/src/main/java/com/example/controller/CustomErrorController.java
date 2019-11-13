package com.example.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.utils.BaseResponse;

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
    BaseResponse<Object> error(HttpServletRequest request, HttpServletResponse response) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String, Object> errorMap = this.errorAttributes.getErrorAttributes(requestAttributes, false);
        BaseResponse<Object> responseData = new BaseResponse<Object>();
        Integer code = -1;
        switch (response.getStatus()) {
        case 200:
            code = 0;
            break;
        }
        responseData.setCode(code);
        responseData.setStatus(response.getStatus());
        responseData.setMessage(errorMap.get("message").toString());
        response.setStatus(HttpServletResponse.SC_OK);
        return responseData;
    }

}
