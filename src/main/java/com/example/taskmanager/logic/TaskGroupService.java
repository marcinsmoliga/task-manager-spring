package com.example.taskmanager.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.taskmanager.model.TaskGroup;
import com.example.taskmanager.model.projection.GroupReadModel;
import com.example.taskmanager.model.projection.GroupWriteModel;
import com.example.taskmanager.repository.TaskGroupRepository;
import com.example.taskmanager.repository.TaskRepository;

@Service
public class TaskGroupService {

	private final TaskGroupRepository repository;
	private final TaskRepository taskRepository;

	TaskGroupService(TaskGroupRepository repository, TaskRepository taskRepository) {
		this.repository = repository;
		this.taskRepository = taskRepository;
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

	public void toggleGroup(int groupId) {
		if(taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)) {
			throw new IllegalStateException("Group has undone tasks!");
		}
		TaskGroup result = repository.findById(groupId)
				.orElseThrow(() -> new IllegalArgumentException("TaskGroup with given id not found!"));
		result.setDone(!result.isDone());

	}
}
