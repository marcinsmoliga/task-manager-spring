package com.example.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.taskmanager.model.Task;

@RepositoryRestResource
interface TaskRepository extends JpaRepository<Task, Integer> {
}
