package com.microsservice.e_mail_sender.service;

import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.microsservice.e_mail_sender.models.EmailRequest;
import com.microsservice.e_mail_sender.repository.EmailRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailService {
	private final JavaMailSender mailSender;
	private final EmailRepository repository;

	  public void sendValidationEmail(EmailRequest request) throws MessagingException, BadRequestException {
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
	        
	        Optional<EmailRequest> userExist = repository.findByEmail(request.getEmail());
	        if (userExist.isPresent()) {
	            throw new BadRequestException("E-mail já enviado.");
	        }

	        String link = "http://localhost:8081/verificado?email="+request.getEmail();
	        String htmlMsg = "<h2>Confirmação de Cadastro</h2>"
	                + "<p>Por favor, clique no botão abaixo para validar sua conta:</p>"
	                + "<a href='" + link + "' style='display:inline-block;padding:10px 20px;color:#fff;background-color:#4CAF50;"
	                + "text-decoration:none;border-radius:5px;'>Validar Conta</a>";
	        
	        
	        helper.setTo(request.getEmail());
	        helper.setSubject("Confirmação de Cadastro");
	        helper.setText(htmlMsg, true); 
	        mailSender.send(mimeMessage);

	        repository.save(request);
	        
	    }
	  
	  public Page<EmailRequest> read(Pageable pageable) {
		  return repository.findAll(pageable);
	  }
	  
	  public EmailRequest findByEmailAndVerify(EmailRequest request) throws BadRequestException {
	   Optional<EmailRequest> userexist = repository.findByEmailAndVerify(request.getEmail());
	   if (userexist.isEmpty()) {
		throw new BadRequestException("usário não foi verificado.");
	}
	   return userexist.get();

	  }
	
}
