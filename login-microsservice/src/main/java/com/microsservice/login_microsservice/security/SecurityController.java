package com.microsservice.login_microsservice.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice.login_microsservice.DTOs.LoginRequestDTO;
import com.microsservice.login_microsservice.DTOs.LoginValidateRequestDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*")
public class SecurityController {
	private final SecurityService service;
	
	@PostMapping("/login")
	public ResponseEntity<LoginValidateRequestDTO> login(@RequestBody @Valid LoginRequestDTO dto){
		try {
			LoginValidateRequestDTO output = service.login(dto);
			return ResponseEntity.ok(output);
		}  catch (Exception e) {
	        System.out.println(e.getMessage());
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	    }
	}
}
