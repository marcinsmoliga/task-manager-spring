package com.example.taskmanager.model;


import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	@NotBlank(message = "Description must not be empty!")
	private String description;

	private boolean done;
	private LocalDateTime deadline;

	@Embedded
	private Audit audit = new Audit();


	public Task() {
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

	public void updateFrom(Task source) {
		description = source.description;
		done = source.done;
		deadline = source.deadline;
	}
}
