package com.example.config;

import org.jodconverter.core.DocumentConverter;
import org.jodconverter.spring.JodConverterBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "jodconverter.local", name = "enabled", havingValue = "true")
@Configuration
public class AppConfig {
    
    @Bean
    @ConfigurationProperties(prefix = "jodconverter.local")
    JodConverterBean jodConverterBean() {
        return new JodConverterBean();
    }
    
    @Bean
    DocumentConverter jodConverter(JodConverterBean jodConverterBean) {
        return jodConverterBean.getConverter();
    }
    

}
