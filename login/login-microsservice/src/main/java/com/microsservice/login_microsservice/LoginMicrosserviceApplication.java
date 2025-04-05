package com.microsservice.login_microsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LoginMicrosserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginMicrosserviceApplication.class, args);
	}

}
