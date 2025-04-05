package com.microsservice.login_microsservice.configs;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microsservice.login_microsservice.models.VerificationRequest;

import jakarta.validation.Valid;

@FeignClient(name = "${spring.application.name}", url = "${cadastro-microsservice}",
configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface CadastroServiceClient {

	@PostMapping("/auth/load")
	Optional<VerificationRequest> load(@RequestBody @Valid VerificationRequest request);
}
