package com.shazzad.jwtspringsecuritydemo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEMA_NAME = "JWT Token";

    @Bean
    public OpenAPI openAPI(){
        OpenAPI openApi = new OpenAPI();
        openApi.setInfo(info());
        openApi.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEMA_NAME));
        openApi.setComponents(apiComponents());
        return openApi;
    }

    @Bean
    public Info info(){
        Info swaggerInfo = new Info();
        swaggerInfo.setTitle("Jwt Demo With Spring Boot");
        swaggerInfo.setVersion("0.1");
        swaggerInfo.setSummary("Develop Spring security with JWT");
        swaggerInfo.setContact(contact());
        swaggerInfo.setLicense(license());
        return swaggerInfo;
    }

    @Bean
    public Contact contact(){
        Contact contact = new Contact();
        contact.setName("Shazzad");
        contact.setEmail("shazzadhk12@gmail.com");
        contact.setUrl("http://localhost:8088");
        return contact;
    }

    @Bean
    public License license(){
        License license = new License();
        license.setName("Jwt License");
        license.setUrl("http://jwt-license.com");
        return license;
    }

    @Bean
    public Components apiComponents(){
        Components components = new Components();
        components.addSecuritySchemes(
            SECURITY_SCHEMA_NAME,
            new SecurityScheme()
                .name(SECURITY_SCHEMA_NAME)
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .scheme("bearer")
                .bearerFormat("JWT")
        );
        return components;
    }
}
