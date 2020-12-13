package com.example.taskmanager.adapter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.model.TaskGroup;
import com.example.taskmanager.repository.TaskGroupRepository;

@Repository
interface SqlTaskGroupRepository extends TaskGroupRepository, JpaRepository<TaskGroup, Integer> {

	@Override
	@Query("select distinct g from TaskGroup g join fetch g.tasks")
	List<TaskGroup> findAll();

	@Override
	boolean existsByDoneIsFalseAndProject_Id(Integer projectId);
}
