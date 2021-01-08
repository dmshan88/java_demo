package com.example.config;

import org.jodconverter.core.DocumentConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.util.PdfConverterUtil;

@Configuration
public class PdfConverterConfig {
    
    @Bean
    public PdfConverterUtil pdfConverterUtil(DocumentConverter converter) {
        return new PdfConverterUtil(converter);
    }
    

}
