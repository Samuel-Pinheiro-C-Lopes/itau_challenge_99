package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.dtos;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.models.TransactionStatistics;

public record TransactionStatisticsResponseDTO(
		Integer count, 
		Double sum, 
		Double max, 
		Double min, 
		Double avg
) {
	public TransactionStatisticsResponseDTO(final TransactionStatistics statistics) {
		this(
				statistics.getCount(), 
				statistics.getSum(), 
				statistics.getMax(), 
				statistics.getMin(), 
				statistics.getAvg()
		);
	}
}
