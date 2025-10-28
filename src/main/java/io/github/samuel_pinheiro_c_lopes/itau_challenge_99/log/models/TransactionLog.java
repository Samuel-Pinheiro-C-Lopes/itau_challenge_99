package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.models;

import java.time.OffsetDateTime;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.enums.OperationType;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.models.Transaction;

public record TransactionLog (Transaction transaction, OffsetDateTime time, OperationType operationType) { }
