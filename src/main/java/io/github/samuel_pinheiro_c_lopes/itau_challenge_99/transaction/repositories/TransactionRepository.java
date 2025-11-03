package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.repositories;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Repository;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.models.Transaction;

@Repository
public class TransactionRepository {
	private final List<Transaction> transactions;
	
	public TransactionRepository() {
		this.transactions = new Vector<Transaction>();
	}
	
	public List<Transaction> findAll() {
		return this.transactions
				.stream()
				.map(t -> t.clone())
				.toList();
	}
	
	public List<Transaction> findByDateTimeGreaterThan(final OffsetDateTime dateTime) {
		return this.transactions
				.stream()
				.filter(t -> t.dateTime().isAfter(dateTime))
				.map(t -> t.clone())
				.toList();
	}
	
	public Boolean save(final Transaction transaction) {
		return this.transactions.add(transaction);
	}
	
	public void deleteAll() {
		this.transactions.clear();
	}
}
