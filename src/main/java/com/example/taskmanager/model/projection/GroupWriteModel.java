package com.example.taskmanager.model.projection;

import java.util.Set;
import java.util.stream.Collectors;

import com.example.taskmanager.model.TaskGroup;

public class GroupWriteModel {

	private String description;
	private Set<GroupTaskWriteModel> tasks;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<GroupTaskWriteModel> getTasks() {
		return tasks;
	}

	public void setTasks(Set<GroupTaskWriteModel> tasks) {
		this.tasks = tasks;
	}

	public TaskGroup toGroup() {
		var result = new TaskGroup();
		result.setDescription(description);
		result.setTasks(
				tasks.stream()
				.map(source -> source.toTask(result))
				.collect(Collectors.toSet())
		);
		return result;
	}
}
