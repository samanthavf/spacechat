package com.microsservice.cadastro.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.microsservice.cadastro.security.SecurityService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfigFilter{
	
	private final SecurityService service;
	
	@Bean
	 SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		return security
				.csrf(csfr -> csfr.disable())
	            .authorizeHttpRequests(auth -> auth
	            .requestMatchers(HttpMethod.POST).permitAll()
	            .requestMatchers(HttpMethod.GET).permitAll()
	            .requestMatchers(HttpMethod.DELETE).permitAll()
	            .anyRequest().authenticated()
	            )
	            .httpBasic(Customizer.withDefaults())
	            .userDetailsService(service)
	            .build();
	}
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
