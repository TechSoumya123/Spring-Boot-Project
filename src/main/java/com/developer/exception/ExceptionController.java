package com.developer.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body("Data integrity violation occurred: " + ex.getRootCause().getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String, String>> httpRequestMethodNotAllowed(
			HttpRequestMethodNotSupportedException exception) {
		Map<String, String> errors = new HashMap<>();
		errors.put("errors", "Request method " + exception.getMethod() + " not supported. Supported methods are "
				+ exception.getSupportedHttpMethods().stream().findFirst().get());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errors);
	}

}
