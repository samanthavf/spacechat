package com.microsservice.login_microsservice.configs;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microsservice.login_microsservice.models.VerificatedEmail;

@FeignClient(name = "${spring.application.name}", url = "${email-service}",
configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface EmailVerificationClient {
	
	@PostMapping("/email/find")
	Optional<VerificatedEmail> userVerify(@RequestBody VerificatedEmail request);
}
