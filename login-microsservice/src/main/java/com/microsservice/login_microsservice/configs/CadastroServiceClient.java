package com.microsservice.login_microsservice.configs;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${spring.application.name}", url = "${cadastro-microsservice}",
configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface CadastroServiceClient {

	@GetMapping("/auth/load")
	void load(@RequestParam String email);
}
