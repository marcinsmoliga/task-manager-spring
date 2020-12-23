package com.example.taskmanager.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
class TaskController {

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	private final TaskRepository repository;

	public TaskController(TaskRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	ResponseEntity<Task> createTask(@RequestBody @Valid Task toCreate) {
		Task result = repository.save(toCreate);
		return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
	}

	@GetMapping(params = {"!sort", "!page", "!size"})
	ResponseEntity<List<Task>> readAllTasks() {
		logger.warn("Exposing all tasks!");
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping
	ResponseEntity<?> readAllTasks(Pageable page) {
		logger.info("Custom pageable");
		return ResponseEntity.ok(repository.findAll(page).getContent());
	}
	@GetMapping("/{id}")
	ResponseEntity<?> readTask(@PathVariable int id) {
		return repository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/search/done")
	ResponseEntity<List<Task>> readDoneTasks(@RequestParam(defaultValue = "true") boolean state) {
		return ResponseEntity.ok(
				repository.findByDone(state)
		);
	}

	@PutMapping("/{id}")
	ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody @Valid Task toUpdate) {
		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		repository.findById(id)
				.ifPresent(task -> {
					task.updateFrom(toUpdate);
					repository.save(task);
				});
		return ResponseEntity.noContent().build();
	}

	@Transactional
	@PatchMapping("/{id}")
	public ResponseEntity<?> toggleTask(@PathVariable int id) {
		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		repository.findById(id)
				.ifPresent(task -> task.setDone(!task.isDone()));
		return ResponseEntity.noContent().build();
	}
}
