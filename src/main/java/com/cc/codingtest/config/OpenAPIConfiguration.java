package com.cc.codingtest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Coding Test API Docs");

        Contact myContact = new Contact();
        myContact.setName("Claudia Cristina");

        Info information = new Info()
                .title("Coding Test API Docs")
                .version("1.0")
                .description("This API exposes endpoints to fetch and manipulate router locations.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
