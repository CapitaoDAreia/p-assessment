package com.pismo.assessment.infrastructure.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pismoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pismo Assessment API")
                        .description("API for managing accounts and transactions")
                        .version("1.0.0")
                        .contact(
                                new Contact()
                                        .name("Pismo Candidate")
                                        .email("candidate@pismo.io")
                        )
                );
    }
}
