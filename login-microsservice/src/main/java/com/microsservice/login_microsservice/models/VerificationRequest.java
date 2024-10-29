package com.microsservice.login_microsservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerificationRequest {
	private String email;
	private String senha;
}
