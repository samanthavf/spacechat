package com.microsservice.e_mail_sender.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microsservice.e_mail_sender.models.EmailRequest;

@Repository
public interface EmailRepository extends JpaRepository<EmailRequest, UUID> {
	void save(String user);

	@Query("SELECT u FROM EmailRequest u WHERE u.email = :email AND u.verificated = true")
	Optional<EmailRequest> findByEmailAndVerify(@Param("email") String email);

	@Query("SELECT u FROM EmailRequest u WHERE u.email= :email")
	Optional<EmailRequest> findByEmail(@Param("email") String email);

}
