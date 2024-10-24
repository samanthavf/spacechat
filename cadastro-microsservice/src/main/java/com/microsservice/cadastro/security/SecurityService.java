package com.microsservice.cadastro.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microsservice.cadastro.DTOs.UserDTO;
import com.microsservice.cadastro.configs.EmailServiceClient;
import com.microsservice.cadastro.models.UsersRequest;
import com.microsservice.cadastro.models.VerificationRequest;
import com.microsservice.cadastro.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SecurityService implements UserDetailsService{
	private final UserRepo repo;
	private final TokenService service;
    private final EmailServiceClient emailServiceClient;

	
	public Map<String, String> register(UserDTO dto) throws Exception {
		   Optional<UsersRequest> userExiste = repo.findByEmail(dto.email());
		    if (userExiste.isPresent()) {
		        throw new Exception("Usuário com e-mail já cadastrado.");
		    }
		
		UsersRequest request = new UsersRequest();
		request.setName(dto.name());
		request.setEmail(dto.email());
		request.setPassword(dto.password());
		repo.save(request);
		
		String Token = service.CreateToken(request);
		
		Map<String, String> response = new HashMap<>();
		response.put("email", request.getEmail());
		response.put("token", Token);
		
		VerificationRequest emailRequest = new VerificationRequest(request.getEmail(), Token);
		emailServiceClient.sendVerificationEmail(emailRequest);
		
		return response;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  Optional<UsersRequest> users = repo.findByEmail(username);
	UsersRequest user = users.orElseThrow(( ) -> new UsernameNotFoundException("Usuário não encontrado" + username));
		return user;
	}

}
