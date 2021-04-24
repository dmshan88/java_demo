package com.example.compoment;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.common.Constants;
import com.example.model.User;

@Component
public class JmsListenerBean {

    @JmsListener(destination = Constants.topic)
    public void processMessage(User content) {
        System.out.println("receive " + content);
    }
}
