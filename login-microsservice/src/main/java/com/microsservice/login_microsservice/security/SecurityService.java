package com.microsservice.login_microsservice.security;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microsservice.login_microsservice.DTOs.LoginRequestDTO;
import com.microsservice.login_microsservice.DTOs.LoginValidateRequestDTO;
import com.microsservice.login_microsservice.configs.CadastroServiceClient;
import com.microsservice.login_microsservice.configs.EmailVerificationClient;
import com.microsservice.login_microsservice.models.LoginRequest;
import com.microsservice.login_microsservice.models.VerificatedEmail;
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
	private final EmailVerificationClient emailVerification;
	private final TokenService service;
	
	public Page<LoginRequest> readUser(Pageable pageable){
		return repo.findAll(pageable);
	}
	
	
	public LoginValidateRequestDTO login(final LoginRequestDTO dto) throws Exception{	
	VerificationRequest request = new VerificationRequest(dto.email(), dto.senha());
	
     Optional<VerificationRequest> users = client.load(request);
     VerificationRequest  user = users.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado " + dto.email())); 	
     
     VerificatedEmail verificatedEmail = new VerificatedEmail(dto.id(),dto.email());
     
     Optional<VerificatedEmail> emailVerify = emailVerification.userVerify(verificatedEmail);
     VerificatedEmail emailValid = emailVerify.orElseThrow(() -> new UsernameNotFoundException("e-mail não foi verificado." + dto.email()));
     
     
     Optional<LoginRequest> findEmail = repo.findByEmail(emailValid.getEmail());
     Optional<LoginRequest> findUser = repo.findByEmail(user.getEmail());
        
      LoginRequest newUser = new LoginRequest();
     
     if (!findEmail.isPresent()) {
    	 newUser.setLogedIn(false);
    	 throw new Exception("E-mail do usuário não foi verficado." + user.getEmail());
    	
	}
        
     if (!findUser.isPresent()) {
     	newUser.setEmail(user.getEmail());
         newUser.setSenha(user.getSenha());
         newUser.setLogedIn(true);
         repo.save(newUser);
		} else {
		    if (!findUser.get().isLogedIn()) { 
		        findUser.get().setLogedIn(true);
		        repo.save(findUser.get());
		    } else {
		        throw new Exception("O usuário já está logado.");
		    }
		    }

	if (!encoder.matches(dto.senha(), user.getSenha())) {
		throw new BadCredentialsException("Senha incorreta para o usuário: " + user.getEmail() + " senha: " + dto.senha());
	}
	
 	final String Token = service.CreateToken(user);

	return new LoginValidateRequestDTO(Token);
	}
	
	public void logout(LoginRequestDTO dto) throws Exception {
		Optional<LoginRequest> findUser = repo.findByEmail(dto.email());
		
		 if (findUser.isPresent()) {
		        LoginRequest user = findUser.get();
		        if (user.isLogedIn()) {
		            user.setLogedIn(false);
		            repo.save(user);
		        } else {
		            throw new Exception("O usuário já está deslogado.");
		        }
		    } else {
		        throw new Exception("Usuário não encontrado.");
		    }
		
	}
	
	public void delete(UUID id) {
		repo.deleteById(id);
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
