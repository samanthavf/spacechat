package com.microsservice.login_microsservice.configs;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.microsservice.login_microsservice.security.SecurityService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component

public class SecurityFilter extends OncePerRequestFilter {
	
	private SecurityService securityService;
	
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
	String header = request.getHeader("Authorization");
	String token = null;
	
	if (header != null && header.startsWith("Bearer")) {
		token = header.substring(7);
	}
	
	if (token != null && securityService.validateToken(token)) {
		Claims claims = securityService.getClaims(token);
		String username = claims.getSubject();
		
		UserDetails details = securityService.loadUserByUsername(username);
		
		UsernamePasswordAuthenticationToken authenticationToken =
		new UsernamePasswordAuthenticationToken(details, null , details.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}
	filterChain.doFilter(request, response);	
}

}