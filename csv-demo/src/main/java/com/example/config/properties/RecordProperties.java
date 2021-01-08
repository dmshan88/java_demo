package com.example.config.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "app.properties.record")
public class RecordProperties {
    
    private String idColumn;
    
    private String titleColumn;
    
    private String authorColumn;
    
    private String contentColumn;
    
    private String categoryIdColumn;
    
    private String dateColumn;
    
    private Map<String, String> additionalColumns = new HashMap<>();

}
