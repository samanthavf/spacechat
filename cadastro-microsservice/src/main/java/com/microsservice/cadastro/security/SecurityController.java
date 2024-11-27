package com.microsservice.cadastro.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice.cadastro.DTOs.UserDTO;
import com.microsservice.cadastro.models.UsersRequest;
import com.microsservice.cadastro.models.VerificationRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class SecurityController {
	private final SecurityService service;
	
	@PostMapping("/register")
	public ResponseEntity<UsersRequest> register(@RequestBody @Valid UserDTO dto){
		try {
			 UsersRequest registeredUser = service.register(dto);
			 return ResponseEntity.ok(registeredUser);
		} catch (Exception e) {
			UsersRequest errorResponse = new UsersRequest();
	        errorResponse.setName("Error");
	        errorResponse.setEmail(e.getMessage());
	        return ResponseEntity.badRequest().body(errorResponse);
		}
		}
	
	@PostMapping("/load")
	public ResponseEntity<VerificationRequest> load(@RequestBody @Valid VerificationRequest user){
		VerificationRequest request = service.load(user);
		return ResponseEntity.ok(request);
	}
	
	}


