package com.microsservice.cadastro.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microsservice.cadastro.models.UsersRequest;
import com.microsservice.cadastro.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{
	private final UserRepo repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  Optional<UsersRequest> users = repository.findByEmail(username);
	UsersRequest user = users.orElseThrow(( ) -> new UsernameNotFoundException("Usuário não encontrado" + username));
		return user;
	}
	

}
