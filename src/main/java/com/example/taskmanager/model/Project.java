package com.example.taskmanager.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Project's description must not be empty!")
	private String description;

	@OneToMany(mappedBy = "project")
	private Set<TaskGroup> groups;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private Set<ProjectStep> steps;

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

	Set<TaskGroup> getGroups() {
		return groups;
	}

	void setGroups(Set<TaskGroup> groups) {
		this.groups = groups;
	}

	Set<ProjectStep> getSteps() {
		return steps;
	}

	void setSteps(Set<ProjectStep> steps) {
		this.steps = steps;
	}
}
