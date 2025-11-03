package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.controllers;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.dtos.TransactionStatisticsResponseDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.services.TransactionStatisticsService;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/estatistica")
public class TransactionStatisticsController {
	private final TransactionStatisticsService transactionStatisticsService;
	
	@Autowired
	public TransactionStatisticsController(final TransactionStatisticsService transactionStatisticsService) {
		this.transactionStatisticsService = transactionStatisticsService;
	}
	
	@GetMapping
	public ResponseEntity<TransactionStatisticsResponseDTO> get(
			@Parameter(
				description = "The duration in seconds defining the recent time window for transactions. Only transactions recorded within the last N seconds (where N is the value of seconds offset) will be included when calculating the statistics report. Transactions older than this window will be ignored.", 
				example = "60"
			)
			@RequestParam(defaultValue = "60") Long secondsOffset) {
		final OffsetDateTime offset = OffsetDateTime.now().minusSeconds(secondsOffset);
		
		final TransactionStatisticsResponseDTO transactionStatistics = 
				this.transactionStatisticsService.getStatistics(offset);
		
		return ResponseEntity.ok(transactionStatistics);
	}
}
