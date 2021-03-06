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
	boolean existsById(Integer id);
	boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);
	Page<Task> findAll(Pageable page);
	List<Task> findByDone(boolean done);

	List<Task> findAllByGroup_Id(Integer groupId);
}
