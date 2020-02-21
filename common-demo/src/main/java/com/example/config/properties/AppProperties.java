package com.example.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "app.demo")
public class AppProperties {
    
    private Integer aa;
    
    private String bb;

}
