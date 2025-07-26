package com.ontheway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*") // Set specific domains in prod!
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(false)
                .maxAge(3600);
    }
}
