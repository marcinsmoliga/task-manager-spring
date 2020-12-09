package com.example.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.model.Task;

@Repository
interface SqlTaskRepository extends TaskRepository, JpaRepository<Task,Integer> {

}
