package com.microsservice.cadastro.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class  WebConfig implements WebMvcConfigurer {
   
    @Bean
    public CorsFilter corsFilter() {
        // Configurar o filtro CORS explicitamente
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:4200"); // Permitir origem do frontend
        config.addAllowedMethod("*"); // Permitir todos os métodos
        config.addAllowedHeader("*"); // Permitir todos os cabeçalhos

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplicar a configuração para todos os endpoints

        return new CorsFilter(source);
    }
}
