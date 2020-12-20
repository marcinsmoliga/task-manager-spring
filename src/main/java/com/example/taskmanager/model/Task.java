package com.example.taskmanager.model;


import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(generator = "inc")
	@GenericGenerator(name = "inc", strategy = "increment")
	private Integer id;

	@NotBlank(message = "Tasks's description must not be empty!")
	private String description;

	private boolean done;
	private LocalDateTime deadline;

	@Embedded
	private Audit audit = new Audit();

	@ManyToOne
	@JoinColumn(name = "task_group_id")
	private TaskGroup group;


	public Task() {
	}

	public Task(String description, LocalDateTime deadline) {
		this.description = description;
		this.deadline = deadline;
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

	public LocalDateTime getDeadline() {
		return deadline;
	}

	void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	TaskGroup getGroup() {
		return group;
	}

	void setGroup(TaskGroup group) {
		this.group = group;
	}

	public void updateFrom(Task source) {
		description = source.description;
		done = source.done;
		deadline = source.deadline;
		group = source.group;
	}
}
