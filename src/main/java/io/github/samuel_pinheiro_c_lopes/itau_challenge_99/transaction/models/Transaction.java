package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.transaction.models;

import java.time.OffsetDateTime;

public record Transaction(Double value, OffsetDateTime dateTime) { }
