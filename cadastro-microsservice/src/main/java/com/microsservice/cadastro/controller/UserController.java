package com.microsservice.cadastro.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice.cadastro.DTOs.UserDTO;
import com.microsservice.cadastro.models.UsersRequest;
import com.microsservice.cadastro.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("cadastro")
@RestController
public class UserController {
	
	private final UserService service;

	@PostMapping("/create")
	public ResponseEntity<UsersRequest> create(@RequestBody @Valid UserDTO dto) throws Exception{
		return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
	}
	
	@GetMapping("/read")
	public ResponseEntity<Page<UsersRequest>> read(Pageable pageable){
		return ResponseEntity.ok(service.read(pageable));
	}
	
	@DeleteMapping(path = "/delete")
	public ResponseEntity<Void> delete(@PathVariable String email){
		service.delete(email);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
