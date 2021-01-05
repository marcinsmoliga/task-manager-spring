package com.example.taskmanager.model.projection;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskGroup;

public class GroupTaskWriteModel {

	@NotBlank(message = "Tasks's description must not be empty!")
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
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
