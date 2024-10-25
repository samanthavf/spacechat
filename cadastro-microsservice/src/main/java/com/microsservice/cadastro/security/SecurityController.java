package com.microsservice.cadastro.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice.cadastro.DTOs.UserDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*")
public class SecurityController {
	private final SecurityService service;
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody @Valid UserDTO dto){
		try {
			Map<String, String> response = service.register(dto);
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			 Map<String, String> errorResponse = new HashMap<>();
		      errorResponse.put("error", e.getMessage());
		      return ResponseEntity.badRequest().body(errorResponse);
		}
		}
	
	@GetMapping("/load")
	public ResponseEntity<UserDetails> load(@RequestParam String email){
		return ResponseEntity.ok(service.loadUserByUsername(email));
	}
	
	}


