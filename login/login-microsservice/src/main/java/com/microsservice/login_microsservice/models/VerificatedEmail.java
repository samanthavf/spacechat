package com.microsservice.login_microsservice.models;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VerificatedEmail {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID) 
	private UUID id;
	private String email;

}
