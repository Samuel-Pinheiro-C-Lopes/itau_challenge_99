package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.enums.OperationType;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.services.TransactionLogService;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.dtos.TransactionRequestDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.models.Transaction;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.repositories.TransactionRepository;

@Service
public class TransactionService {
	private final TransactionRepository transactionRepository;
	private final TransactionLogService transactionLogService;
	
	@Autowired
	public TransactionService(final TransactionRepository transactionRepository, final TransactionLogService transactionLogService) {
		this.transactionRepository = transactionRepository;
		this.transactionLogService = transactionLogService;
	}
	
	public void save(final TransactionRequestDTO transactionRequest) {
		final Transaction transaction = transactionRequest.toTransaction();
		
		this.transactionRepository.save(transaction);		
		
		this.transactionLogService.save(transaction, OperationType.SAVE);
	}
	
	public void deleteAll() {
		final List<Transaction> transactions = this.transactionRepository.findAll();
		
		this.transactionRepository.deleteAll();

		this.transactionLogService.save(transactions, OperationType.DELETE);
	}
}
