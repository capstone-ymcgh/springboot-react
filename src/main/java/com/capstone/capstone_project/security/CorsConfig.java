package com.capstone.capstone_project.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/*
 * Cross-Origin Resource Sharing (CORS)를 활성화하기 위한 설정 클래스
 * CORS는 웹 브라우저가 한 출처에서 다른 출처로의 비인가된 요청을 방지하기 위해 구현한 보안 기능
 */

@Configuration
public class CorsConfig {
    /* CORS */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 리소스에 액세스할 수 있는 출처를 지정, http://localhost:3000에서의 요청을 허용
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));

        // 리소스에 액세스할 수 있는 HTTP 메서드를 지정, GET, POST, OPTIONS, PATCH, DELETE를 포함
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS", "PATCH", "DELETE"));

        // 요청에서 허용된 HTTP 헤더를 지정, Content-Type 및 Authorization 헤더를 허용
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

        // URL 패턴을 기반으로 하는 CORS 설정을 위한 설정 소스, 지정된 URL 패턴(/**)에 대해 CorsConfiguration을 등록, 이는 애플리케이션의 모든 엔드포인트에 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
