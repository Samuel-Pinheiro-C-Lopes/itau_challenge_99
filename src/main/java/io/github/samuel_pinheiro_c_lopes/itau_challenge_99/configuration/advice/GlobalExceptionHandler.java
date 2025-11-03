package io.github.samuel_pinheiro_c_lopes.itau_challenge_99.configuration.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Void> handleInvalidValues(final MethodArgumentNotValidException ex) {
		return ResponseEntity.status(422).build();
	}
}
