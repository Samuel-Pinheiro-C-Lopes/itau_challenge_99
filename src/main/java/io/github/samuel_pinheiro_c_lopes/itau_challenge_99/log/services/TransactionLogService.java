package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.services;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.dtos.TransactionLogResponseDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.enums.OperationType;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.models.TransactionLog;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.repositories.TransactionLogRepository;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.models.Transaction;

@Service
public class TransactionLogService {
	private final TransactionLogRepository transactionLogRepository;
	
	@Autowired
	public TransactionLogService(final TransactionLogRepository transactionLogRepository) {
		this.transactionLogRepository = transactionLogRepository;
	}
	
	public void save(final List<Transaction> transactions, final OperationType operationType) {
		for (final Transaction transaction : transactions)
			this.save(transaction, operationType);
	}
	
	public void save(final Transaction transaction, final OperationType operationType) {
		this.transactionLogRepository.save(new TransactionLog(transaction, OffsetDateTime.now(), operationType));
	}
	
	public List<TransactionLogResponseDTO> findAll() {
		List<TransactionLogResponseDTO> stats = this.transactionLogRepository.findAll()
				.stream()
				.map(TransactionLogResponseDTO::new)
				.toList();
		
		return stats;
	}
	
	public void deleteAll() {
		this.transactionLogRepository.deleteAll();
	}
}
