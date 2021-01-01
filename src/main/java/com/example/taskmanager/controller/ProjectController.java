package com.example.taskmanager.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.taskmanager.logic.ProjectService;
import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.ProjectStep;
import com.example.taskmanager.model.projection.ProjectWriteModel;

@Controller
@RequestMapping("/projects")
class ProjectController {

	private final ProjectService service;

	ProjectController(ProjectService service) {
		this.service = service;
	}

	@GetMapping
	String showProjects(Model model) {
		var projectToEdit = new ProjectWriteModel();
		projectToEdit.setDescription("project to edit");
		model.addAttribute("project", projectToEdit);
		return "projects";
	}

	@PostMapping
	String addProject(@ModelAttribute("project") @Valid ProjectWriteModel current, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "projects";
		}
		service.save(current);
		model.addAttribute("project", new ProjectWriteModel());
		model.addAttribute("projects", getProjects());
		model.addAttribute("message", "New Project has been Created and Saved");
		return "projects";
	}

	@PostMapping(params ="addStep")
	String addProjectStep(@ModelAttribute("project") ProjectWriteModel current) {
		current.getSteps().add(new ProjectStep());
		return "projects";
	}

	@PostMapping("/{id}")
	String createGroup(@ModelAttribute("project") ProjectWriteModel current,
	                   Model model,
	                   @PathVariable int id,
	                   @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")LocalDateTime deadline) {
		try {
			service.createGroup(deadline, id);
			model.addAttribute("message", "The Group has been added!");
		} catch (IllegalStateException | IllegalArgumentException e) {
			model.addAttribute("message", "An error occurred while creating the group!");
		}
		return "projects";
	}

	@ModelAttribute("projects")
	List<Project> getProjects() {
		return service.readAll();
	}

}
