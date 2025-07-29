package com.mrrice.movieflix.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // allow all endpoints
                        .allowedOrigins("http://localhost:5173") // allow Vite frontend
                        .allowedMethods("*") // allow GET, POST, etc
                        .allowedHeaders("*"); // allow all headers
            }
        };
    }
}
