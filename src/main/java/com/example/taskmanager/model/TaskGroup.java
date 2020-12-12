package com.example.taskmanager.model;


import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "task_groups")
public class TaskGroup {

	@Id
	@GeneratedValue(generator = "inc")
	@GenericGenerator(name = "inc", strategy = "increment")
	private Integer id;

	@NotBlank(message = "Task Group description must not be empty!")
	private String description;

	private boolean done;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
	private Set<Task> tasks;


	public TaskGroup() {
	}

	public Integer getId() {
		return id;
	}

	void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	void setDescription(String description) {
		this.description = description;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
}
