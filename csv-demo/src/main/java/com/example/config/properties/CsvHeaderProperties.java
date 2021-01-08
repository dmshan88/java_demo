package com.example.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "app.properties.csvheader")
public class CsvHeaderProperties {
    
    private String urlColumn;
    
    private String titleColumn;
    
    private String authorColumn;
    
    private String contentColumn;
    
    private String dateColumn;

}
