package com.example.demo.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

@Configuration
public class SwaggerUI{



    @Bean
    public GroupedOpenApi patientGroup() {
        return GroupedOpenApi
                .builder()
                .group("patients")
                .pathsToMatch("api/patient/**")
                .build();


    }
    @Bean
    public GroupedOpenApi roleGroup() {
        return GroupedOpenApi
                .builder()
                .group("role")
                .pathsToMatch("api/role/**")
                .build();


    }
    @Bean
    public GroupedOpenApi userGroup() {
        return GroupedOpenApi
                .builder()
                .group("user")
                .pathsToMatch("/user/**")
                .build();


    }
    @Bean
    public GroupedOpenApi getReview() {
        return GroupedOpenApi
                .builder()
                .group("review")
                .pathsToMatch("/api/review/**")
                .build();


    }
    @Bean
    public GroupedOpenApi getAnalyse() {
        return GroupedOpenApi
                .builder()
                .group("analyse")
                .pathsToMatch("/api/analyse/**")
                .build();


    }
    @Bean
    public GroupedOpenApi getAnalyseInvoice() {
        return GroupedOpenApi
                .builder()
                .group("analyseinvoice")
                .pathsToMatch("/api/analyses_invoice/**")
                .build();


    }
}
