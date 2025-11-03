package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.dtos.TransactionStatisticsResponseDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.models.Transaction;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.repositories.TransactionRepository;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class TransactionStatisticsServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionStatisticsService transactionStatisticsService;

    private Transaction t1, t2, t3;

    @BeforeEach
    void setUp() {
        t1 = new Transaction(100.0, OffsetDateTime.now().minusSeconds(10));
        t2 = new Transaction(300.0, OffsetDateTime.now().minusSeconds(20));
        t3 = new Transaction(600.0, OffsetDateTime.now().minusSeconds(30));
    }

    @Test
    @DisplayName("Should calculate correct statistics from recent transactions")
    void testGetStatistics_ComputesCorrectValues() {
        when(transactionRepository.findByDateTimeGreaterThan(any(OffsetDateTime.class)))
                .thenReturn(List.of(t1, t2, t3));

        TransactionStatisticsResponseDTO result =
                transactionStatisticsService.getStatistics(OffsetDateTime.now().minusMinutes(1));

        assertThat(result).isNotNull();
        assertThat(result.sum()).isEqualTo(1000.0);
        assertThat(result.count()).isEqualTo(3L);
        assertThat(result.avg()).isEqualTo(1000.0 / 3);
        assertThat(result.max()).isEqualTo(600.0);
        assertThat(result.min()).isEqualTo(100.0);
    }

    @Test
    @DisplayName("Should return empty statistics when no transactions are found")
    void testGetStatistics_NoTransactions() {
        when(transactionRepository.findByDateTimeGreaterThan(any(OffsetDateTime.class)))
                .thenReturn(List.of());

        TransactionStatisticsResponseDTO result =
                transactionStatisticsService.getStatistics(OffsetDateTime.now().minusMinutes(1));

        assertThat(result).isNotNull();
        assertThat(result.count()).isEqualTo(0L);
        assertThat(result.sum()).isEqualTo(0.0);
        assertThat(result.avg()).isEqualTo(0.0);
        assertThat(result.max()).isEqualTo(0.0);
        assertThat(result.min()).isEqualTo(0.0);
    }
}
