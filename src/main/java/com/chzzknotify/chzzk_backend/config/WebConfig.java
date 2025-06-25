package com.chzzknotify.chzzk_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173", "https://www.mjdev.kr", "http://www.mjdev.kr") // React dev 서버 주소
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Spring 6.x: ** 뒤에는 경로 변수 허용 안되므로, 고정 깊이로 처리
        registry.addViewController("/{path}")
                .setViewName("forward:/index.html");
        registry.addViewController("/{path1}/{path2}")
                .setViewName("forward:/index.html");
        registry.addViewController("/{path1}/{path2}/{path3}")
                .setViewName("forward:/index.html");
        registry.addViewController("/{path1}/{path2}/{path3}/{path4}")
                .setViewName("forward:/index.html");
    }
}

