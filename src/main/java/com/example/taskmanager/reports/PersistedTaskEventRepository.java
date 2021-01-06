package com.example.taskmanager.reports;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface PersistedTaskEventRepository extends JpaRepository<PersistedTaskEvent, Integer> {
	List<PersistedTaskEvent> findByTaskId(int taskId);
}
