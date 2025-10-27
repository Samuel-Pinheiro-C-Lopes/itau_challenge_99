package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.services;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.dtos.TransactionStatisticsDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.models.Transaction;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.models.TransactionStatistics;
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
	
	public TransactionStatisticsDTO getStatistics(final OffsetDateTime offset) {
		final List<Transaction> transactions = this.transactionRepository.findByDateTimeGreaterThan(offset);
		final TransactionStatistics statistics = new TransactionStatistics();
		
		transactions.forEach(t ->transactionStatisticsService.updateStatistics(statistics, t));
		
		return new TransactionStatisticsDTO(statistics);
	}
}
