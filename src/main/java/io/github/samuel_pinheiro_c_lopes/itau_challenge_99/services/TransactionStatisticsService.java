package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.services;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.dtos.TransactionStatisticsResponseDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.models.Transaction;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.models.TransactionStatistics;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.repositories.TransactionRepository;

@Service
public class TransactionStatisticsService {
	private final TransactionRepository transactionRepository;
	
	@Autowired
	public TransactionStatisticsService(final TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	
	public TransactionStatisticsResponseDTO getStatistics(final OffsetDateTime offset) {
		final List<Transaction> transactions = this.transactionRepository.findByDateTimeGreaterThan(offset);
		final TransactionStatistics statistics = new TransactionStatistics();
		this.updateStatistics(statistics, transactions);
		return new TransactionStatisticsResponseDTO(statistics);
	}
	
	
	private void updateStatistics(final TransactionStatistics statistics, final List<Transaction> newTransactions) {
		newTransactions.forEach(t -> this.updateStatisticsExceptAvg(statistics, t));
		this.setNewAvg(statistics);
	}
	
	private void updateStatistics(final TransactionStatistics statistics, final Transaction newTransaction) {
		this.increaseCount(statistics);
		this.increaseSum(statistics, newTransaction.value());
		this.setNewMax(statistics, newTransaction.value());
		this.setNewMin(statistics, newTransaction.value());
		this.setNewAvg(statistics);
	}
	
	private void updateStatisticsExceptAvg(final TransactionStatistics statistics, final Transaction newTransaction) {
		this.increaseCount(statistics);
		this.increaseSum(statistics, newTransaction.value());
		this.setNewMax(statistics, newTransaction.value());
		this.setNewMin(statistics, newTransaction.value());
	}
	
	private void setNewAvg(final TransactionStatistics statistics) {
		if (statistics.getSum() != null && statistics.getCount() != null)
			statistics.setAvg(Long.divideUnsigned(statistics.getSum(), statistics.getCount()));
	}
	
	private void setNewMax(final TransactionStatistics statistics, final Long maxCadidate) {
		statistics.setMax(Long.max(statistics.getMax(), maxCadidate));
	}
	
	private void setNewMin(final TransactionStatistics statistics, final Long minCandidate) {
		statistics.setMin(Long.min(statistics.getMin(), minCandidate));
	}
	
	private void increaseCount(final TransactionStatistics statistics) {
		if (statistics.getCount() != null)
			statistics.setCount(statistics.getCount() + 1);
	}
	
	private void increaseSum(final TransactionStatistics statistics, final Long value) {
		if (statistics.getSum() != null)
			statistics.setSum(statistics.getSum() + value);
	}
}
