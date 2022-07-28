package com.example.demo.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

import java.util.List;

@Configuration
public class SwaggerUI{



    @Bean
    public GroupedOpenApi departmentGroup() {
        return GroupedOpenApi
                .builder()
                .group("department")
                .pathsToMatch("/api/department/**")
                .build();


    }

    @Bean
    public GroupedOpenApi authGroup() {
        return GroupedOpenApi
                .builder()
                .group("Authentication")
                .pathsToMatch("/api/auth/**")
                .build();


    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .servers()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat("JWT").scheme("bearer"))
                )
                .info(new Info()
                        .title("Hero Clinic integration module")
                        .version("V1")
                        .license(new License().name("Rstudio offerta").url("https://rstudio-hospital.herokuapp.com/"))
                );
    }
}
