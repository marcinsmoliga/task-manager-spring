package com.example.taskmanager.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Description must not be empty!")
	private String description;
	private boolean done;

	public Task() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	void setDone(boolean done) {
		this.done = done;
	}
}
