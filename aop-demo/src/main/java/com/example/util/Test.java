package com.example.util;

import com.example.aop.AuthToken;

//@Component
public class Test {
    
    @AuthToken
    public void doSometing() {
        System.out.println("do someing");
    }

}
