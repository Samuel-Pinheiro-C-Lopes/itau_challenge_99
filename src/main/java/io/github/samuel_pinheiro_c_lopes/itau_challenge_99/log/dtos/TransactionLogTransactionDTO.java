package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.dtos;

import java.time.OffsetDateTime;

public record TransactionLogTransactionDTO(Double valor, OffsetDateTime dataHora) { }
