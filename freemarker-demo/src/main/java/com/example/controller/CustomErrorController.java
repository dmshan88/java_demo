package com.example.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import com.example.common.CustomResponse;
import com.example.common.ErrorCode;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @GetMapping(value = PATH, produces = MediaType.TEXT_HTML_VALUE)
    String errorHtml(HttpServletRequest request, HttpServletResponse response, Model model) {
        ErrorMessage errorMessage = new ErrorMessage();
        getCodeAndMessage(request, response, errorMessage);
        model.addAttribute("message", errorMessage.getMessage());
        model.addAttribute("code", errorMessage.getCode());
        return "error/error";
    }

    @ResponseBody
    @RequestMapping(value = PATH)
    CustomResponse<Object> error(HttpServletRequest request, HttpServletResponse response) {
        ErrorMessage errorMessage = new ErrorMessage();
        getCodeAndMessage(request, response, errorMessage);
        response.setStatus(HttpServletResponse.SC_OK);
        return CustomResponse.error(errorMessage.getCode(), errorMessage.getMessage());
    }

    private void getCodeAndMessage(HttpServletRequest request, HttpServletResponse response,
            ErrorMessage errorMessage) {
        ServletWebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> errorMap = this.errorAttributes.getErrorAttributes(webRequest, false);
        ErrorCode errorCode = ErrorCode.ERROR;
        String message = "未知错误";

        switch (response.getStatus()) {
        // 对不能捕获的403等异常手动赋值
        case 403:
            errorCode = ErrorCode.AUTH_ERROR;
            message = "403 拒绝访问";
            break;
        case 404:
            errorCode = ErrorCode.AUTH_ERROR;
            message = "404 无页面";
            break;
        default:
            // 获取错误信息
            message = errorMap.get("message").toString();
        }
        errorMessage.setCode(errorCode.getCode());
        errorMessage.setMessage(message);
    }

    private class ErrorMessage {
        private Integer code;
        private String message;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

}