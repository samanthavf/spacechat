package com.microsservice.e_mail_sender.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsservice.e_mail_sender.models.EmailRequest;
import com.microsservice.e_mail_sender.service.EmailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("email")
public class EmailController {
	private final EmailService service;
	
	@PostMapping("/send")
	public ResponseEntity<EmailRequest> sendVerificationEmail(@RequestBody EmailRequest request) throws MessagingException{
		service.sendValidationEmail(request);
		return ResponseEntity.ok(request);
	}
	
}
