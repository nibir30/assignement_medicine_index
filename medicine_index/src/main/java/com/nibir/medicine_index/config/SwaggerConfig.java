package com.nibir.medicine_index.config;

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

    @Bean
    public OpenAPI myOpenAPI() {

//        Server devServer = new Server();
//        devServer.setUrl("http://localhost:9009/idra_wp_cms/");
//        devServer.setDescription("Server URL in Development environment");
//
//        Server prodServer = new Server();
//        prodServer.setUrl("http://localhost:9004/idra_wp_cms/");
//        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setName("Doclive Java Team");
        contact.setUrl("https://www.example.com");
        contact.setEmail("r.nibir30@gmail.com");

        License mitLicense = new License().name("MIT License").url("https://www.example.com/licenses/mit/");

        Info info = new Info()
                .title("Doclive Java Team")
                .version("1.0.0")
                .contact(contact)
                .description("This API exposes endpoints to manage Doclive admin panel, Website and Mobile App.")
                .termsOfService("https://www.example.com/terms")
                .license(mitLicense);

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Authentication");

        Components components = new Components()
                .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme());

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
        // .servers(List.of(devServer, prodServer));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

}
