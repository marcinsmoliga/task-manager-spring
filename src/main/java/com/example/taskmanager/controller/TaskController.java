package com.example.taskmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.taskmanager.repository.TaskRepository;

@RepositoryRestController
class TaskController {

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	private final TaskRepository repository;

	public TaskController(TaskRepository repository) {
		this.repository = repository;
	}

	@GetMapping(value = "/tasks", params = {"!sort", "!page", "!size"})
	ResponseEntity<?> readAllTasks() {
		logger.warn("Exposing all tasks!");
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/tasks")
	ResponseEntity<?> readAllTasks(Pageable page) {
		logger.info("Custom pageable");
		return ResponseEntity.ok(repository.findAll(page));
	}
}