package com.microsservice.login_microsservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationRequest {
	private String name;
	private String email;
	private String senha;
	
	public VerificationRequest(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
}
