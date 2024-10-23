package com.microsservice.cadastro.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.microsservice.cadastro.models.UsersRequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;

@Data
@Service
public class TokenService {
	private final Key key;
	
	public TokenService() {
		this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}
	
	public String CreateToken(UsersRequest user) {
		return Jwts.builder()
		.setSubject(user.getEmail())
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis()+ 3600000))
		.signWith(key, SignatureAlgorithm.HS256)
		.compact();
	}
}
