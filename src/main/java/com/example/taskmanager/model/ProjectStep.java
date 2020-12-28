package com.example.taskmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "project_steps")
public class ProjectStep {

	@Id
	@GeneratedValue(generator = "inc")
	@GenericGenerator(name = "inc", strategy = "increment")
	private int id;

	@NotBlank(message = "Project step's description must not be empty!")
	private String description;

	private int daysToDeadline;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	public ProjectStep() {

	}

	public int getId() {
		return id;
	}

	void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDaysToDeadline() {
		return daysToDeadline;
	}

	public void setDaysToDeadline(int daysToDeadline) {
		this.daysToDeadline = daysToDeadline;
	}

	Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
