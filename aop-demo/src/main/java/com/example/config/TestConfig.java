package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.util.Test;

@Configuration
public class TestConfig {
    
    @Bean
    public Test test() {
        return new Test();
    }

}
