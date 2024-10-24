package com.microsservice.cadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CadastroMicrosserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroMicrosserviceApplication.class, args);
	}

}
