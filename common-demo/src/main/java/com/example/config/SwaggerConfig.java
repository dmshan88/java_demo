package com.example.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    
    private static final String SWAGGER_PROPERTY_ENABLE = "app.swagger.enable";
    private static final String SWAGGER_PROPERTY_TITLE = "app.swagger.title";
    private static final String SWAGGER_PROPERTY_DESCRIPTION = "app.swagger.description";
    private static final String SWAGGER_PROPERTY_VERSION = "app.swagger.version";
    
    private static final String REQUEST_PROPERTY_TOKEN_NAME = "app.request.token.name";
    private static final String REQUEST_PROPERTY_TOKEN_DESCRIPTION = "app.request.token.description";
    private static final String REQUEST_PROPERTY_TOKEN_TYPE = "app.request.token.type";
    
    private final Environment environment;

    public SwaggerConfig(Environment environment) {
        this.environment = environment;
    }
    
    @Bean
    public Docket customImplementation(){
        String name = this.environment.getProperty(REQUEST_PROPERTY_TOKEN_NAME, "token");
        String description = this.environment.getProperty(REQUEST_PROPERTY_TOKEN_DESCRIPTION, "令牌值");
        String type = this.environment.getProperty(REQUEST_PROPERTY_TOKEN_TYPE, "query");
        Boolean enable = this.environment.getProperty(SWAGGER_PROPERTY_ENABLE, Boolean.class, false);
        List<Parameter> operationParameters = new ArrayList<Parameter>();
        Parameter tokenParameter = new ParameterBuilder()
                .name(name)
                .description(description)
                .modelRef(new ModelRef("string"))
                .parameterType(type)
                .required(false)
                .build();
        operationParameters.add(tokenParameter);
        return new Docket(DocumentationType.SWAGGER_2)
            .enable(enable)
            .select()
            .build()
            .globalOperationParameters(operationParameters)
            .apiInfo(apiInfo());

    }
    
    ApiInfo apiInfo() {
        String title = this.environment.getProperty(SWAGGER_PROPERTY_TITLE, "API 文档");
        String description = this.environment.getProperty(SWAGGER_PROPERTY_DESCRIPTION, "参考");
        String version = this.environment.getProperty(SWAGGER_PROPERTY_VERSION, "1.0");
        return new ApiInfoBuilder()
            .title(title)
            .description(description)
            .license("")
            .termsOfServiceUrl("")
            .version(version)
            .build();
    }
}
