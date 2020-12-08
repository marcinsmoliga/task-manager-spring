package com.example.taskmanager.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
class Task {

	@Id
	private int id;
	private String description;
	private boolean done;

	Task() {
	}

	int getId() {
		return id;
	}

	void setId(int id) {
		this.id = id;
	}

	String getDescription() {
		return description;
	}

	void setDescription(String description) {
		this.description = description;
	}

	boolean isDone() {
		return done;
	}

	void setDone(boolean done) {
		this.done = done;
	}
}
