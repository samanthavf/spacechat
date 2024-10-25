package com.microsservice.login_microsservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microsservice.login_microsservice.models.LoginRequest;

import feign.Param;

@Repository
public interface LoginRepo extends JpaRepository<LoginRequest, UUID> {

	boolean existsByEmail(String email);

	void deleteByEmail(String email);

	@Query("SELECT l FROM LoginRequest l WHERE l.email= :email")
	Optional<LoginRequest> findByEmail(@Param("email") String email);

}
