package com.microsservice.login_microsservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfigs {
	
	@Bean
	 SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		return security
				.csrf(csfr -> csfr.disable())
	            .authorizeHttpRequests(auth -> auth
	    	    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
	            .requestMatchers(HttpMethod.GET, "/login/read").permitAll()
	            .requestMatchers(HttpMethod.DELETE ,"/login/delete/**").permitAll()
	            .anyRequest().authenticated()
	            )
	            .httpBasic(Customizer.withDefaults())
	            .build();
	}
	
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
