package com.microsservice.e_mail_sender.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailService {
	
	private final JavaMailSender mailSender;
	
	
	public void sendValidationEmail(String toEmail, String token){
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(toEmail);
		mailMessage.setSubject("Confirmação de Cadastro");
		mailMessage.setText("Por favor, clique no link abaixo para validar sua conta:\n"
                + "http://localhost:8080/validar?token=" + token);
		
		mailSender.send(mailMessage);
	}

}
