package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}