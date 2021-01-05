package com.example.taskmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
class IllegalExceptionsControllerAdvice {

	@ExceptionHandler(IllegalArgumentException.class)
	ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e) {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(IllegalStateException.class)
	ResponseEntity<String> handleIllegalArgument(IllegalStateException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
