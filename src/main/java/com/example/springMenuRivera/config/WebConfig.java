package com.example.springMenuRivera.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapea la URL /img/ a la carpeta física "uploads" y a la carpeta "static/img"
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:uploads/", "classpath:/static/img/");
    }
}