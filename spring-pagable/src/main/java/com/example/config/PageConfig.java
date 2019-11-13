package com.example.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.controller.DemoController;

@Configuration
public class PageConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setPageParameterName(DemoController.PARAM_PAGE_NAME);
        resolver.setSizeParameterName(DemoController.PARAM_SIZE_NAME);
        argumentResolvers.add(resolver);

        super.addArgumentResolvers(argumentResolvers);
    }
}
