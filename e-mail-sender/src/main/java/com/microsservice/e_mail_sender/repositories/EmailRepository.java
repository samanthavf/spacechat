package com.microsservice.e_mail_sender.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microsservice.e_mail_sender.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {

}
