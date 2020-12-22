package com.example.taskmanager.model.projection;

import java.time.LocalDateTime;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskGroup;

public class GroupTaskWriteModel {
	private String description;
	private LocalDateTime deadline;

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(final LocalDateTime deadline) {
		this.deadline = deadline;
	}

	Task toTask(TaskGroup group) {
		return new Task(description, deadline, group);
	}
}
