package com.example.taskmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.example.taskmanager.model.Task;

public interface TaskRepository {

	List<Task> findAll();
	Optional<Task> findById(Integer id);
	Task save(Task entity);
	Page<Task> findAll(Pageable page);
	List<Task> findByDone(@Param("state") boolean done);
}
