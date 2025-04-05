package com.microsservice.cadastro.configs;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microsservice.cadastro.models.VerificationRequest;

@FeignClient(name = "${spring.application.name}" , url = "${email-service}" ,
configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface EmailServiceClient {
	
	 @PostMapping("/email/send")
	    void sendVerificationEmail(@RequestBody VerificationRequest request);
}
