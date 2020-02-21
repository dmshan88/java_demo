package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.SpringDataWebConfiguration;

@Configuration
public class SpringDataWebConfig extends SpringDataWebConfiguration {
    
    @Bean
    public PageableHandlerMethodArgumentResolver pageableResolver() {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver(sortResolver());
        resolver.setPageParameterName("page1");
        resolver.setSizeParameterName("limit");
        return resolver;
    }

}
