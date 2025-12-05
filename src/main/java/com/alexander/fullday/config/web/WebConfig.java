package com.alexander.fullday.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Todas las rutas
                .allowedOrigins("*") // Permite todos los orígenes
                .allowedMethods("*") // Permite todos los métodos
                .allowedHeaders("*") // Permite todos los headers
                .allowCredentials(false);
    }
}