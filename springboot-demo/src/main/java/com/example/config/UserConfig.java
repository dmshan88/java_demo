package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.properties.UserProperties;

@Configuration
public class UserConfig {

    @ConfigurationProperties(prefix = "app.user")
    @Bean
    public UserProperties userProperties() {
        return new UserProperties();
    }
}
