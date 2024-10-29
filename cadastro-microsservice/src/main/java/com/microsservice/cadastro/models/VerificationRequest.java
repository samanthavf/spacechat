package com.microsservice.cadastro.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerificationRequest{
	private String email;
	private String senha;
}
