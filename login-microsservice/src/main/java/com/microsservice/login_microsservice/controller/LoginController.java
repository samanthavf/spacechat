package com.microsservice.login_microsservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice.login_microsservice.models.LoginRequest;
import com.microsservice.login_microsservice.service.LoginService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("login")
@RestController
public class LoginController {
	private final LoginService service;
	
	@GetMapping("/read")
	public ResponseEntity<Page<LoginRequest>> read(Pageable pageable){
		return ResponseEntity.ok(service.read(pageable));
	}
	
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<Void> delete(@PathVariable String email){
		service.delete(email);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
}
