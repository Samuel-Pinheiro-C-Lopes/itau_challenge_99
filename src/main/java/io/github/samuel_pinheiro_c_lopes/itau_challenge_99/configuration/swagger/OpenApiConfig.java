package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Itau challenge 99 API")
						.version("1.0.0")
						.description("API to handle transactions and transactions statistics to comply with Itau Challenge number 99 specifications.")
				);
	}
}
