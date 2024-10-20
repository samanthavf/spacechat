package com.microsservice.cadastro.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.microsservice.cadastro.DTOs.UserDTO;
import com.microsservice.cadastro.models.UsersRequest;
import com.microsservice.cadastro.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepo repo;
	
	
	//create
	public UsersRequest create(UserDTO dto) throws Exception {
		List<UsersRequest> userExiste = repo.findByEmail(dto.email());
		if (!userExiste.isEmpty()) {
			throw new Exception("Usuário com e-mail cadastrado");
			}
		UsersRequest newUser = new UsersRequest();
		newUser.setName(dto.name());
		newUser.setEmail(dto.email());
		newUser.setPassword(dto.password());
		return repo.save(newUser);
	}
	
	//read
	public Page<UsersRequest> read(Pageable pageable){
		return repo.findAll(pageable);
	}
	
	//delete by email
	public void delete(String email){
		if (!repo.existsByEmail(email)) {
			throw new EntityNotFoundException("Cliente não encontrado");
		}
		repo.deleteByEmail(email);
	}
	
}
