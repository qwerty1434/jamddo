package com.jamddo.global.config;

import com.jamddo.global.jwt.JwtFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .exposedHeaders(JwtFilter.AUTHORIZATION_HEADER) // 프론트에서 해당 헤더를 받아볼 수 있게 만듬
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.HEAD.name(),
                HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name());

    }
}