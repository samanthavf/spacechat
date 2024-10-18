package com.microsservice.e_mail_sender.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.microsservice.e_mail_sender.enums.StatusEmail;
import com.microsservice.e_mail_sender.models.EmailModel;
import com.microsservice.e_mail_sender.repositories.EmailRepository;

import jakarta.transaction.Transactional;

@Service
public class EmailService {
	final JavaMailSender mailSender;
	final EmailRepository emailRepository;
	
	public EmailService(EmailRepository repository, JavaMailSender emailSender) {
		this.mailSender = emailSender;
		this.emailRepository = repository;
	}
		
	@Value(value = "${spring.mail.username}")
	private String emailFrom;
	
	@Transactional
	public EmailModel sendEmail(EmailModel model) {
		try {
			model.setSendDateEmail(LocalDateTime.now());
			model.setEmailFrom(emailFrom);
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(model.getEmailTo());
			message.setSubject(model.getSubject());
			message.setText(model.getText());
			
			mailSender.send(message);
			
			model.setStatusEmail(StatusEmail.SENT);
			
		} catch (MailException e) {
			 model.setStatusEmail(StatusEmail.ERROR);
		}finally {
			return emailRepository.save(model);
		}
	}

}
