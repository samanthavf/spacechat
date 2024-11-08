package com.microsservice.login_microsservice.security;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice.login_microsservice.DTOs.LoginRequestDTO;
import com.microsservice.login_microsservice.DTOs.LoginValidateRequestDTO;
import com.microsservice.login_microsservice.models.LoginRequest;

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
	
	@PutMapping("/logout")
	public ResponseEntity<String> logout(@RequestBody @Valid  LoginRequestDTO dto) {
	    try {
	        service.logout(dto);
	        return ResponseEntity.ok("Logout realizado com sucesso.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
	
	@GetMapping("/read")
	public ResponseEntity<Page<LoginRequest>> findAll(Pageable pageable){
		return ResponseEntity.ok(service.readUser(pageable));
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id){
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
