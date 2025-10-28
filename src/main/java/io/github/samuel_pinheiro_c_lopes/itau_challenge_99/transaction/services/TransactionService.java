package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.dtos.TransactionRequestDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.repositories.TransactionRepository;

@Service
public class TransactionService {
	private final TransactionRepository transactionRepository;
	
	@Autowired
	public TransactionService(final TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	
	public Boolean save(final TransactionRequestDTO transactionRequest) {
		return this.transactionRepository.save(transactionRequest.toTransaction());
	}
	
	public void deleteAll() {
		this.transactionRepository.deleteAll();
	}
}
