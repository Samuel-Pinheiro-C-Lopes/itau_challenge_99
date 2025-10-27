package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.dtos;

import java.time.OffsetDateTime;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.models.Transaction;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record TransactionRequestDTO(
		@NotNull
		@Min(0l)
		Long valor,
		@NotNull
		@PastOrPresent
		OffsetDateTime dataHora
	) {
	public Transaction toTransaction() {
		return new Transaction(this.valor(), this.dataHora());
	}
}
