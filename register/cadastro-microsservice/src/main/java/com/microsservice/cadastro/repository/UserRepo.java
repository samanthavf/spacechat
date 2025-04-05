package com.microsservice.cadastro.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microsservice.cadastro.models.UsersRequest;

public interface UserRepo extends JpaRepository<UsersRequest, UUID>{

	@Query("SELECT u FROM UsersRequest u WHERE u.email= :email")
	Optional<UsersRequest> findByEmail(@Param("email") String email);
	
	boolean existsByEmail(String email);
	
	void deleteByEmail(String email);

}
