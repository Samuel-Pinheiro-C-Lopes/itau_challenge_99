package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.dtos;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record TransactionRequestDTO(
		@NotNull
		Long valor,
		@NotNull
		@PastOrPresent
		OffsetDateTime dataHora
	) {
	

}
