package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.dtos.TransactionRequestDTO;
import io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.services.TransactionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {
	@MockitoBean
	private TransactionService transactionService;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private TransactionRequestDTO transactionRequest;
    
    @Autowired
    public TransactionControllerTest(final MockMvc mockMvc, final ObjectMapper objectMapper) {
    	this.mockMvc = mockMvc;
    	this.objectMapper = objectMapper;
    }

    @BeforeEach
    void setUp() {
        transactionRequest = new TransactionRequestDTO(150.75, OffsetDateTime.now());
    }

    @Test
    @DisplayName("POST /transacao should return 201 Created when transaction is saved successfully")
    void testPostTransaction_ReturnsCreated() throws Exception {
    	when(transactionService.save(any(TransactionRequestDTO.class))).thenReturn(Boolean.TRUE);

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionRequest)))
                .andExpect(status().isCreated());

        Mockito.verify(transactionService).save(any(TransactionRequestDTO.class));
    }

    @Test
    @DisplayName("DELETE /transacao should return 200 OK when all transactions are deleted")
    void testDeleteTransactions_ReturnsOk() throws Exception {
        doNothing().when(transactionService).deleteAll();

        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());

        Mockito.verify(transactionService).deleteAll();
    }
}
