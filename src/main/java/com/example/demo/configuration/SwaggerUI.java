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

@Configuration
public class SwaggerUI{

    public final static  String AccessToken = "Authorization";

    @Bean
    public GroupedOpenApi systemControllerGroup() {
        return GroupedOpenApi
                .builder()
                .group("Authentiction")
                .pathsToMatch("/api/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi departmentGroup() {
        return GroupedOpenApi
                .builder()
                .group("Department")
                .pathsToMatch("/api/department/**")
                .build();
    }
    @Bean
    public GroupedOpenApi adminsGroup() {
        return GroupedOpenApi
                .builder()
                .group("MANAGER-APIs")
                .pathsToMatch("/api/**")
                .build();
    }


    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("/").description("Base application"))
                .components(new Components()
                        .addSecuritySchemes(AccessToken,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .bearerFormat("JWT")
                                        .scheme("Bearer"))
                )
                .info(new Info()
                        .title("Hospital api integration module")
                        .version("V1")
                        .license(new License().name("Hospital api").url("https://application.uz"))
                );
    }
}