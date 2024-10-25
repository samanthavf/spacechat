package com.microsservice.login_microsservice.security;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microsservice.login_microsservice.DTOs.LoginRequestDTO;
import com.microsservice.login_microsservice.configs.CadastroServiceClient;
import com.microsservice.login_microsservice.models.LoginRequest;
import com.microsservice.login_microsservice.repository.LoginRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SecurityService implements UserDetailsService{
	private final LoginRepo repo;
	private final PasswordEncoder encoder;
	private final CadastroServiceClient client;
	
	public LoginRequest login(LoginRequestDTO dto) {
     Optional<LoginRequest> users = repo.findByEmail(dto.email());
     LoginRequest user = users.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
     client.load(user.getEmail());
     
	if (!encoder.matches(dto.senha(), user.getSenha())) {
		throw new BadCredentialsException("Senha incorreta para o usuário: " + user.getEmail());
	}
	return user;
	
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<LoginRequest> users = repo.findByEmail(username);
		  LoginRequest user = users.orElseThrow(( ) -> new UsernameNotFoundException("Usuário não encontrado" + username));
		  return user;
	}


}
