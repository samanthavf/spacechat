package com.microsservice.e_mail_sender.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microsservice.e_mail_sender.models.EmailRequest;
import com.microsservice.e_mail_sender.repository.EmailRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class VerifyController {
	private final EmailRepository repository;

	
	@GetMapping("/verificado")
	public String showVerificationPage(@RequestParam String email) {
		Optional<EmailRequest> userOptional = repository.findByEmail(email);
		if (userOptional.isPresent()) {
	        EmailRequest user = userOptional.get();
	        user.setVerificated(true);
	        repository.save(user);
	   }		
		return "UsuarioVerificado";
	}
}
