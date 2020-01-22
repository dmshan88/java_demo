package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.util.AppPushUtil;
import com.example.util.AppPushUtil.Message;

@RestController
public class MainController {
    
    @Autowired
    private AppPushUtil pushUtil;

    @GetMapping(path = "/test")
    void test(String message) {
        if (message == null || message.isEmpty()) {
            message = "default alert";
        }

        Message pubshMessage = new Message();
        pubshMessage.setContent(message);
        pubshMessage.setTitle("hahah");
        pushUtil.pushAliasOne("ssadfsce", pubshMessage);
    }
    
    @GetMapping(path = "/test1")
    void test1(String alsis) {
        if (alsis == null || alsis.isEmpty()) {
            alsis = "ssadfsce";
        }
        pushUtil.setAlias("13065ffa4e66a6568f8", "ssadfsce");
    }
    

}
