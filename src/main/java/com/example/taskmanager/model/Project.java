package com.example.taskmanager.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(generator = "inc")
	@GenericGenerator(name = "inc", strategy = "increment")
	private int id;

	@NotBlank(message = "Project's description must not be empty!")
	private String description;

	@OneToMany(mappedBy = "project")
	private Set<TaskGroup> groups;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private Set<ProjectStep> steps;

	public Project() {

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

	public void setDescription(String description) {
		this.description = description;
	}

	Set<TaskGroup> getGroups() {
		return groups;
	}

	void setGroups(Set<TaskGroup> groups) {
		this.groups = groups;
	}

	public Set<ProjectStep> getSteps() {
		return steps;
	}

	public void setSteps(Set<ProjectStep> steps) {
		this.steps = steps;
	}
}
