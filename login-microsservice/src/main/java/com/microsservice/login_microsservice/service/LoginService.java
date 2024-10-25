package com.microsservice.login_microsservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.microsservice.login_microsservice.models.LoginRequest;
import com.microsservice.login_microsservice.repository.LoginRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginService {
	private final LoginRepo repo;
	
	public Page<LoginRequest> read(Pageable pageable){
		return repo.findAll(pageable);
	}
	
	@Transactional
	public void delete(String email){
		if (!repo.existsByEmail(email)) {
			throw new EntityNotFoundException("Cliente não encontrado");
		}
		repo.deleteByEmail(email);
	}
	
}
