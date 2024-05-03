package com.juno.simple.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        Info info = new Info()
                .title("Simple API")
                .version("1.0.0")
                .description("Simple API Documentation")
                ;

        return new OpenAPI()
                .info(info);
    }
}
