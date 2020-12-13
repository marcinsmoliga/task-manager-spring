package com.example.taskmanager.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskGroupRepository;

@Service
class TempService {

	List<String> temp(TaskGroupRepository repository) {
		return repository.findAll().stream()
				.flatMap(taskGroup -> taskGroup.getTasks().stream())
				.map(Task::getDescription)
				.collect(Collectors.toList());
	}
}
