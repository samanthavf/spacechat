package com.microsservice.e_mail_sender.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "VALID_USERS")
public class EmailRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID) 
	private UUID id;
	private String email;
	private boolean verificated = false;
}
