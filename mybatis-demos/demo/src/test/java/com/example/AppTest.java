package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.enums.UserTypeEnum;


@SpringBootTest
class AppTest {

    @Test
    public void test() {
        System.out.println("test run");
        UserTypeEnum[] enums = UserTypeEnum.values();
        for (UserTypeEnum object : enums) {
            System.out.println(object.name());
            System.out.println(object.ordinal());
            System.out.println(object);
        }
        
    }
    

}
