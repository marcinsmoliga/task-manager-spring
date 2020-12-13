package com.example.taskmanager.repository;

import java.util.List;
import java.util.Optional;

import com.example.taskmanager.model.TaskGroup;

public interface TaskGroupRepository {
	List<TaskGroup> findAll();
	Optional<TaskGroup> findById(Integer id);
	TaskGroup save(TaskGroup entity);
	boolean existsByDoneIsFalseAndProject_Id(Integer projectId);

}
