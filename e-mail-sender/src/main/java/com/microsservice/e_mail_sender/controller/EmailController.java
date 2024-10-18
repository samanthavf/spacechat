package com.microsservice.e_mail_sender.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice.e_mail_sender.service.EmailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/email")
public class EmailController {

	private final EmailService service;
	
	public ResponseEntity<String> enviarEmailValidacao(@RequestParam String email, @RequestParam String token){
		service.sendValidationEmail(email, token);
		return ResponseEntity.ok("E-mail de validação enviado com sucesso para " + email);
	}
}
