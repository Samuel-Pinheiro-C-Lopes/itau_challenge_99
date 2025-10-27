package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.controllers;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.dtos.TransactionStatisticsResponseDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.services.TransactionStatisticsService;

@RestController
@RequestMapping("/estatistica")
public class TransactionStatisticsController {
	private final TransactionStatisticsService transactionStatisticsService;
	
	@Autowired
	public TransactionStatisticsController(final TransactionStatisticsService transactionStatisticsService) {
		this.transactionStatisticsService = transactionStatisticsService;
	}
	
	@GetMapping
	public ResponseEntity<TransactionStatisticsResponseDTO> get() {
		final OffsetDateTime offset = OffsetDateTime.now().minusMinutes(1l);
		
		final TransactionStatisticsResponseDTO transactionStatistics = 
				this.transactionStatisticsService.getStatistics(offset);
		
		return ResponseEntity.ok(transactionStatistics);
	}
}
