package com.example.taskmanager.reports;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;

@RestController
@RequestMapping("/reports")
class ReportController {
	private final TaskRepository taskRepository;
	private  final PersistedTaskEventRepository eventRepository;

	ReportController(TaskRepository taskRepository,
	                 PersistedTaskEventRepository eventRepository) {
		this.taskRepository = taskRepository;
		this.eventRepository = eventRepository;
	}

	@GetMapping("/count/{id}")
	ResponseEntity<TaskWithChangesCount> readTaskWithCount(@PathVariable int id) {
		return taskRepository.findById(id)
				.map(task -> new TaskWithChangesCount(task, eventRepository.findByTaskId(id)))
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	private static class TaskWithChangesCount {
		public String description;
		public boolean done;
		public int changesCount;

		public TaskWithChangesCount(Task task, List<PersistedTaskEvent> events) {
			description = task.getDescription();
			done = task.isDone();
			changesCount = events.size();
		}
	}
}