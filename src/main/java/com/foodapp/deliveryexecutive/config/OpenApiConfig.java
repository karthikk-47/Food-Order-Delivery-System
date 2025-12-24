package com.foodapp.deliveryexecutive.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI (Swagger) configuration for the HomeBites Food Delivery API.
 * 
 * Access Swagger UI at: http://localhost:8082/swagger-ui.html
 * Access API Docs at: http://localhost:8082/v3/api-docs
 */
@Configuration
public class OpenApiConfig {

    @Value("${server.port:8082}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("HomeBites Food Delivery API")
                        .version("1.0.0")
                        .description("""
                                API documentation for HomeBites Food Delivery Application.

                                ## Authentication
                                Most endpoints require JWT authentication. Use the `/api/auth/login` or role-specific
                                login endpoints to obtain a token, then click the 'Authorize' button above and enter
                                your token in the format: `Bearer <your-token>`

                                ## Roles
                                - **USER**: Regular customers who order food
                                - **HOMEMAKER**: Home chefs who prepare food
                                - **DELIVERYEXECUTIVE**: Delivery partners who deliver orders
                                - **ADMIN**: Administrators with full access

                                ## Rate Limiting
                                API calls may be rate limited. Please handle 429 responses appropriately.
                                """)
                        .contact(new Contact()
                                .name("HomeBites Support")
                                .email("support@homebites.com")
                                .url("https://homebites.com"))
                        .license(new License()
                                .name("Private License")
                                .url("https://homebites.com/license")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:" + serverPort)
                                .description("Local Development Server"),
                        new Server()
                                .url("https://api.homebites.com")
                                .description("Production Server")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter your JWT token obtained from login endpoints")));
    }
}
