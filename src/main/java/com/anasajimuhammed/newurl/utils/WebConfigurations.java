package com.anasajimuhammed.newurl.utils;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfigurations implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**") // Patterns to include, /** means all paths
                .allowedOrigins("*") // Origins allowed to access
                .allowedMethods("GET", "POST", "PUT", "DELETE") // HTTP methods allowed
                .allowedHeaders("*"); // Headers allowed
//                .allowCredentials(true) // Whether to send cookies
//                .maxAge(3600); // Pre-flight cache duration
    }
}
