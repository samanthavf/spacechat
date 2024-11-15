package com.microsservice.e_mail_sender.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<EmailRequest> sendVerificationEmail(@RequestBody EmailRequest request) throws MessagingException, BadRequestException{
		service.sendValidationEmail(request);
		return ResponseEntity.ok(request);
	}
	
	@GetMapping("/read")
	public ResponseEntity<Page<EmailRequest>> read(Pageable pageable){
		return ResponseEntity.ok(service.read(pageable));
	}
	
}
