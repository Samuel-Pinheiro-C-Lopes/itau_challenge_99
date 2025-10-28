package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.controllers;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.dtos.TransactionStatisticsResponseDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.services.TransactionStatisticsService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.OffsetDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionStatisticsController.class)
class TransactionStatisticsControllerTest {
	@MockitoBean
	private TransactionStatisticsService transactionStatisticsService;
	private final MockMvc mockMvc;

    @Autowired
    public TransactionStatisticsControllerTest(final MockMvc mockMvc) {
		this.mockMvc = mockMvc;
    }
    
	@Test
    @DisplayName("GET /estatistica should return 200 OK with transaction statistics")
    void testGetStatistics_ReturnsOk() throws Exception {
        TransactionStatisticsResponseDTO mockResponse = 
        		new TransactionStatisticsResponseDTO(3, 1200.50, 150.05, 700.25, 400.17);

        when(transactionStatisticsService.getStatistics(any(OffsetDateTime.class)))
                .thenReturn(mockResponse);

        mockMvc.perform(get("/estatistica")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sum").value(mockResponse.sum()))
                .andExpect(jsonPath("$.avg").value(mockResponse.avg()))
                .andExpect(jsonPath("$.max").value(mockResponse.max()))
                .andExpect(jsonPath("$.min").value(mockResponse.min()))
                .andExpect(jsonPath("$.count").value(mockResponse.count()));
    }
}
