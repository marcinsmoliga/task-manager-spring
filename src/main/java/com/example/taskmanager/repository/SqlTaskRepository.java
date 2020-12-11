package com.example.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.model.Task;

@Repository
interface SqlTaskRepository extends TaskRepository, JpaRepository<Task,Integer> {

	@Override
	@Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 FROM tasks WHERE id=:id")
	boolean existsById(@Param("id") Integer id);
}
