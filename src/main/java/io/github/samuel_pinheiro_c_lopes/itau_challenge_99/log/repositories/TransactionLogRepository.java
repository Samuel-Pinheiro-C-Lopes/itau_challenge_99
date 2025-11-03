package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.repositories;

import java.util.Vector;
import java.util.List;

import org.springframework.stereotype.Repository;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.models.TransactionLog;

@Repository
public class TransactionLogRepository {
	private List<TransactionLog> transactionLogs;
	
	public TransactionLogRepository() {
		this.transactionLogs = new Vector<TransactionLog>();
	}
	
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
