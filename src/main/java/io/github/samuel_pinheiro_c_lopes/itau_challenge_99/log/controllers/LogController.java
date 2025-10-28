package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.dtos.TransactionLogResponseDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.log.services.TransactionLogService;

@RestController
@RequestMapping("/log")
public class LogController {
	private final TransactionLogService transactionLogService;

	@Autowired
	public LogController(TransactionLogService transactionLogService) {
		this.transactionLogService = transactionLogService;
	}
	
	@GetMapping("/transacao")
	public ResponseEntity<List<TransactionLogResponseDTO>> get() {
		return ResponseEntity.status(200).body(this.transactionLogService.findAll());
	}
	
}
