package com.example.taskmanager.repository;

import java.util.List;
import java.util.Optional;

import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.TaskGroup;

public interface ProjectRepository {
	List<Project> findAll();
	Optional<Project> findById(Integer id);
	Project save(Project entity);

}
