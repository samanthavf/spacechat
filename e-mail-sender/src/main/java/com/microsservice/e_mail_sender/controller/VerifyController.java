package com.microsservice.e_mail_sender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VerifyController {

	@GetMapping("/verificado")
	public String showVerificationPage() {
		return "UsuarioVerificado";
	}
}
