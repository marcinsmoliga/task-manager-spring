package com.example.taskmanager.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskGroup;
import com.example.taskmanager.model.projection.GroupReadModel;
import com.example.taskmanager.model.projection.GroupWriteModel;
import com.example.taskmanager.repository.TaskGroupRepository;

@Service
public class TaskGroupService {

	private final TaskGroupRepository repository;

	TaskGroupService(TaskGroupRepository repository) {
		this.repository = repository;
	}

	public GroupReadModel createGroup(GroupWriteModel source) {
		TaskGroup result = repository.save(source.toGroup());
		return  new GroupReadModel(result);
	}

	public List<GroupReadModel> readAll() {
		return repository.findAll().stream()
				.map(GroupReadModel::new)
				.collect(Collectors.toList());
	}
}
