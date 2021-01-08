package com.example.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "app.properties.config")
public class ConfigProperties {
    
    private String tableName;
    
    private Boolean enableIdColumn;
    
    private Boolean enableSanitizeHtml;

}
