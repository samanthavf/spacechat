package com.microsservice.cadastro.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private final PasswordEncoder encoder;
    private final EmailServiceClient emailServiceClient;
    
	
	public UsersRequest register(UserDTO dto) throws Exception {
		   Optional<UsersRequest> userExiste = repo.findByEmail(dto.email());
		    if (userExiste.isPresent()) {
		        throw new Exception("Usuário com e-mail já cadastrado.");
		    }
		
		UsersRequest request = new UsersRequest();
		request.setName(dto.name());
		request.setEmail(dto.email());
		request.setPassword(encoder.encode(dto.password()));
		UsersRequest savedUser = repo.save(request);
				
		VerificationRequest emailRequest = new VerificationRequest(request.getEmail(), request.getPassword());
		emailServiceClient.sendVerificationEmail(emailRequest);
		
		return savedUser;
	}
	
	public VerificationRequest load(VerificationRequest request) throws UsernameNotFoundException {
		  Optional<UsersRequest> users = repo.findByEmail(request.getEmail());
		  UsersRequest user = users.orElseThrow(( ) -> new UsernameNotFoundException("Usuário não encontrado" + request));
		  return new VerificationRequest(user.getEmail(), user.getPassword());
		}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  Optional<UsersRequest> users = repo.findByEmail(username);
	  UsersRequest user = users.orElseThrow(( ) -> new UsernameNotFoundException("Usuário não encontrado" + username));
	  return user;
	}

}
