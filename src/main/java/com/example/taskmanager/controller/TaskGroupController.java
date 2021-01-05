package com.example.taskmanager.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.logic.TaskGroupService;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.projection.GroupReadModel;
import com.example.taskmanager.model.projection.GroupWriteModel;
import com.example.taskmanager.repository.TaskRepository;

@RestController
@RequestMapping("/groups")
class TaskGroupController {

	private static final Logger logger = LoggerFactory.getLogger(TaskGroupController.class);
	private final TaskGroupService service;
	private final TaskRepository repository;

	TaskGroupController(TaskGroupService service, TaskRepository repository) {
		this.service = service;
		this.repository = repository;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	String showGroups(Model model) {
		model.addAttribute("group", new GroupWriteModel());
		return "groups";
	}

	@ResponseBody
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<GroupReadModel> createGroup(@RequestBody @Valid GroupWriteModel toCreate) {
		GroupReadModel result = service.createGroup(toCreate);
		return  ResponseEntity.created(URI.create("/" + result.getId())).body(result);
	}

	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<GroupReadModel>> readAllGroups() {
		return ResponseEntity.ok(service.readAll());
	}

	@ResponseBody
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Task>> readAllTasksFromGroup(@PathVariable int id) {
		return ResponseEntity.ok(repository.findAllByGroup_Id(id));
	}

	@ResponseBody
	@Transactional
	@PatchMapping("/{id}")
	public ResponseEntity<?> toggleGroup(@PathVariable int id) {
		service.toggleGroup(id);
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(IllegalArgumentException.class)
	ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e) {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(IllegalStateException.class)
	ResponseEntity<String> handleIllegalArgument(IllegalStateException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
