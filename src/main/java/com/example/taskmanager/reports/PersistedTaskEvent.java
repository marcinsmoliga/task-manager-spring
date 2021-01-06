package com.example.taskmanager.reports;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.taskmanager.model.event.TaskEvent;

@Entity
@Table("task_events")
class PersistedTaskEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	int taskId;
	String name;
	LocalDateTime occurrence;

	public PersistedTaskEvent() {
	}

	PersistedTaskEvent(TaskEvent source) {
		taskId = source.getTaskId();
		name = source.getClass().getSimpleName();
		occurrence = LocalDateTime.ofInstant(source.getOccurrence(), ZoneId.systemDefault());
	}
}
