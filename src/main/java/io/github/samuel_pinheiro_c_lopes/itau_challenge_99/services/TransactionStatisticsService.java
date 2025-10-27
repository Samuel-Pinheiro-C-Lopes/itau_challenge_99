package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.services;

import org.springframework.stereotype.Service;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.models.Transaction;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.models.TransactionStatistics;

@Service
public class TransactionStatisticsService {
	
	public void updateStatistics(final TransactionStatistics statistics, final Transaction newTransaction) {
		this.increaseCount(statistics);
		this.increaseSum(statistics, newTransaction.value());
		this.setNewMax(statistics, newTransaction.value());
		this.setNewMin(statistics, newTransaction.value());
		this.setNewAvg(statistics);
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
