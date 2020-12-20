package com.example.taskmanager.adapter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.model.Project;
import com.example.taskmanager.repository.ProjectRepository;

@Repository
interface SqlProjectRepository extends ProjectRepository, JpaRepository<Project, Integer> {
	@Override
	@Query("from Project p join fetch p.steps")
	List<Project> findAll();
}
