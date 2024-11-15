package com.microsservice.e_mail_sender.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.microsservice.e_mail_sender.models.EmailRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	private final JavaMailSender mailSender;
	
	public EmailService(JavaMailSender emailSender) {
		this.mailSender = emailSender;
	}
	

	  public void sendValidationEmail(EmailRequest request) throws MessagingException {
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

	        String link = "https://www.youtube.com/watch?v=7f76eIdqUdY&t=11s";
	        //String link = "http://localhost:8081/verificado"; Confirmação de Cadastro , Por favor, clique no botão abaixo para validar sua conta:
	        String htmlMsg = "<h2>Helloo its meee</h2>"
	                + "<p>Olha o teste aê</p>"
	                + "<a href='" + link + "' style='display:inline-block;padding:10px 20px;color:#fff;background-color:#4CAF50;"
	                + "text-decoration:none;border-radius:5px;'>Validar Conta</a>";

	        helper.setTo(request.getEmail());
	        helper.setSubject("Confirmação de Cadastro");
	        helper.setText(htmlMsg, true); 

	        mailSender.send(mimeMessage);
	    }
	
}
