package com.example.apidemo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String LICENSE = "All rights reserved by Koratsama";

    @Value("${info.build.timestamp}")
    private String timestamp;

    @Value("${info.swagger.contact.name}")
    private String contactName;
    
    @Value("${info.swagger.contact.email}")
    private String contactEmail;

    @Value("${info.build.description}")
    private String projectDescription;

    @Value("${info.swagger.title}")
    private String swaggerTitle;

    @Value("${info.build.pom_version}")
    private String version;

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName(contactName);
        contact.setEmail(contactEmail);

        License license = new License();
        license.setName(LICENSE);

        return new OpenAPI()
            .components(new Components())
            .info(
                new Info().title(swaggerTitle)
                .description(projectDescription)
                .contact(contact)
                .license(license)
                .version(version + " (" + timestamp + ")"));
    }

}
