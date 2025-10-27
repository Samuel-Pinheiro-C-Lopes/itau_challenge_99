package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.dtos;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.models.TransactionStatistics;

public record TransactionStatisticsResponseDTO(
		Integer count, 
		Long sum, 
		Long max, 
		Long min, 
		Long avg
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
