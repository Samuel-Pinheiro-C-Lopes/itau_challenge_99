package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.repositories;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.models.Transaction;

@Service
public class TransactionRepository {
	private final List<Transaction> transactions;
	
	public TransactionRepository() {
		this.transactions = new ArrayList<Transaction>();
	}
	
	public List<Transaction> findAll() {
		return this.transactions;
	}
	
	public List<Transaction> findByDateTimeGreaterThan(final OffsetDateTime dateTime) {
		return this.transactions
				.stream()
				.filter(t -> t.dateTime().isAfter(dateTime))
				.toList();
	}
	
	public Boolean save(final Transaction transaction) {
		return this.transactions.add(transaction);
	}
	
	public void deleteAll() {
		this.transactions.clear();
	}
}
