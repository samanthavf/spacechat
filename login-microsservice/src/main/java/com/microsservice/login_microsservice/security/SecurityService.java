package com.microsservice.login_microsservice.security;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microsservice.login_microsservice.DTOs.LoginRequestDTO;
import com.microsservice.login_microsservice.DTOs.LoginValidateRequestDTO;
import com.microsservice.login_microsservice.configs.CadastroServiceClient;
import com.microsservice.login_microsservice.models.LoginRequest;
import com.microsservice.login_microsservice.models.VerificationRequest;
import com.microsservice.login_microsservice.repository.LoginRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SecurityService implements UserDetailsService{
	private final LoginRepo repo;
	private final PasswordEncoder encoder;
	private final CadastroServiceClient client;
	private final TokenService service;
	
	
	public LoginValidateRequestDTO login(final LoginRequestDTO dto) {		
	VerificationRequest request = new VerificationRequest(dto.email(), dto.senha());
     Optional<VerificationRequest> users = client.load(request);
     VerificationRequest  user = users.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado " + dto.email()));
     
     
	if (!encoder.matches(dto.senha(), user.getSenha())) {
		throw new BadCredentialsException("Senha incorreta para o usuário: " + user.getEmail() + " senha: " + dto.senha());
	}
	
	final String Token = service.CreateToken(user);
	
	return new LoginValidateRequestDTO(Token);
	
	}
	
	
	public boolean validateToken(String token) {
		try {
			 Jwts.parserBuilder()
			.setSigningKey(service.getKey())
			.build()
			.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
		.setSigningKey(service.getKey())
		.build()
		.parseClaimsJws(token)
		.getBody();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<LoginRequest> users = repo.findByEmail(username);
		LoginRequest user = users.orElseThrow(( ) -> new UsernameNotFoundException("Usuário não encontrado" + username));
		  return user;
	}

}
