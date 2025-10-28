package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.dtos.TransactionRequestDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.models.Transaction;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.repositories.TransactionRepository;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private TransactionService transactionService;

    @Test
    @DisplayName("Should save transaction and return true when repository save succeeds")
    void testSaveTransaction_Success() {
        TransactionRequestDTO request = new TransactionRequestDTO(250.0, OffsetDateTime.now());

        when(transactionRepository.save(any(Transaction.class))).thenReturn(true);

        Boolean result = transactionService.save(request);

        assertThat(result).isTrue();
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Should save transaction and return false when repository save fails")
    void testSaveTransaction_Failure() {
        TransactionRequestDTO request = new TransactionRequestDTO(100.0, OffsetDateTime.now());

        when(transactionRepository.save(any(Transaction.class))).thenReturn(false);

        Boolean result = transactionService.save(request);

        assertThat(result).isFalse();
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Should delete all transactions by calling repository.deleteAll()")
    void testDeleteAllTransactions() {
        doNothing().when(transactionRepository).deleteAll();

        transactionService.deleteAll();

        verify(transactionRepository, times(1)).deleteAll();
    }
}
