package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.dtos.TransactionRequestDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.services.TransactionService;

@RestController
@RequestMapping("/transacao")
public class TransactionController {
	private final TransactionService transactionService;
	
	@Autowired
	public TransactionController(final TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@PostMapping
	public ResponseEntity<Void> post(@RequestBody final TransactionRequestDTO transactionRequest) {
		this.transactionService.save(transactionRequest);
		return ResponseEntity.status(201).build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete() {
		this.transactionService.deleteAll();
		return ResponseEntity.status(200).build();
	}
}
