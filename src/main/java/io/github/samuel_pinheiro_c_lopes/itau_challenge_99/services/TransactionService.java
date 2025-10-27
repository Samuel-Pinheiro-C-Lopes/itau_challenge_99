package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.dtos.TransactionRequestDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.repositories.TransactionRepository;

@Service
public class TransactionService {
	private final TransactionStatisticsService transactionStatisticsService;
	private final TransactionRepository transactionRepository;
	
	@Autowired
	public TransactionService(final TransactionRepository transactionRepository, final TransactionStatisticsService transactionStatisticsService) {
		this.transactionRepository = transactionRepository;
		this.transactionStatisticsService = transactionStatisticsService;
	}
	
	public Boolean save(final TransactionRequestDTO transactionRequest) {
		return this.transactionRepository.save(transactionRequest.toTransaction());
	}
	
	public void deleteAll() {
		this.transactionRepository.deleteAll();
	}
}
