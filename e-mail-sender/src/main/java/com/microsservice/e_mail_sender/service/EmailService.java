package com.microsservice.e_mail_sender.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.microsservice.e_mail_sender.models.EmailRequest;

@Service
public class EmailService {
	private final JavaMailSender mailSender;
	
	public EmailService(JavaMailSender emailSender) {
		this.mailSender = emailSender;
	}
	
	public void sendValidationEmail(EmailRequest request){
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(request.getEmail());
		mailMessage.setSubject("Confirmação de Cadastro");
		mailMessage.setText("Por favor, clique no link abaixo para validar sua conta:\n"
                + "http://localhost:8080/validar?token=" + request.getToken());
		
		mailSender.send(mailMessage);
	}

}
