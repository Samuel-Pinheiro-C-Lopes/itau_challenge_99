package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.repositories;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.models.TransactionLog;

@Service
public class TransactionLogRepository {
	private List<TransactionLog> transactionLogs;
	
	public List<TransactionLog> findAll() {
		return this.transactionLogs;
	}
	
	public Boolean save(final TransactionLog transactionLog) {
		return this.transactionLogs.add(transactionLog);
	}
	
	public void deleteAll() {
		this.transactionLogs.clear();
	}
}
