package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.dtos;

import java.time.OffsetDateTime;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.enums.OperationType;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.models.TransactionLog;

public record TransactionLogResponseDTO(TransactionLogTransactionDTO transacao, OffsetDateTime dataLog, OperationType tipoOperacao) {
	public TransactionLogResponseDTO(TransactionLog transactionLog) {
		this(
			new TransactionLogTransactionDTO(
					transactionLog.transaction().value(), 
					transactionLog.transaction().dateTime()
			), 
			transactionLog.time(), 
			transactionLog.operationType()
		);
	}
}
