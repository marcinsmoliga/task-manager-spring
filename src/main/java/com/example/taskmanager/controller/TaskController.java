package com.example.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.example.taskmanager.repository.TaskRepository;

@RepositoryRestController
class TaskController {

	
	private final TaskRepository repository;

	public TaskController(TaskRepository repository) {
		this.repository = repository;
	}
}
