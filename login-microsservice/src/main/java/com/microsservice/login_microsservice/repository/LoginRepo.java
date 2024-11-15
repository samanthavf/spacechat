package com.microsservice.login_microsservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microsservice.login_microsservice.models.LoginRequest;

@Repository
public interface LoginRepo extends JpaRepository<LoginRequest, UUID> {

	@Query("SELECT l FROM LoginRequest l WHERE l.email= :email")
	Optional<LoginRequest> findByEmail(@Param("email") String email);
	
	@Query("SELECT l FROM LoginRequest l WHERE l.email= :email")
	List<LoginRequest> findByEmailList(@Param("email")String email);

	@Query("SELECT l FROM LoginRequest l WHERE l.logedIn = :logedIn")
	Optional<LoginRequest> findByLogedIn(@Param("logedIn") boolean logedIn);

	

	
	
}
