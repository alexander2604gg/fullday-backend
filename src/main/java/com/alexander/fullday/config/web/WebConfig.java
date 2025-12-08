package com.alexander.fullday.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Todas las rutas
                .allowedOrigins("https://fullday2025unt.vercel.app") // Solo tu front
                .allowedMethods("*") // Todos los métodos permitidos (GET, POST, etc.)
                .allowedHeaders("*") // Todos los headers permitidos
                .allowCredentials(true); // Si necesitas enviar cookies o headers de autenticación
    }
}