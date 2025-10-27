package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.models;

import java.time.OffsetDateTime;

public record Transaction(Long value, OffsetDateTime dateTime) { }
