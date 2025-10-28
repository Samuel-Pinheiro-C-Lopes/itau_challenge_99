package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.services;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.dtos.TransactionStatisticsResponseDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.models.Transaction;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.models.TransactionStatistics;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.repositories.TransactionRepository;

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
		
		if (transactions.size() == 0) {
			this.setNewMax(statistics, 0d);
			this.setNewMin(statistics, 0d);
		} else {
			this.updateStatistics(statistics, transactions);
		}
		
		return new TransactionStatisticsResponseDTO(statistics);
	}
	
	// updating statistics behaviors
	
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
	
	// setters for statistics
	
	private void setNewAvg(final TransactionStatistics statistics) {
		if (statistics.getSum() == null || statistics.getCount() == null)
			return;
		
		if (statistics.getSum() == 0d || statistics.getCount() == 0d)
			return;
			
		statistics.setAvg(statistics.getSum() / statistics.getCount());
	}
	
	private void setNewMax(final TransactionStatistics statistics, final Double maxCandidate) {
		statistics.setMax(Double.max(statistics.getMax(), maxCandidate));
	}
	
	private void setNewMin(final TransactionStatistics statistics, final Double minCandidate) {
		statistics.setMin(Double.min(statistics.getMin(), minCandidate));
	}
	
	private void increaseCount(final TransactionStatistics statistics) {
		if (statistics.getCount() != null)
			statistics.setCount(statistics.getCount() + 1);
	}
	
	private void increaseSum(final TransactionStatistics statistics, final Double value) {
		if (statistics.getSum() != null)
			statistics.setSum(statistics.getSum() + value);
	}
}
