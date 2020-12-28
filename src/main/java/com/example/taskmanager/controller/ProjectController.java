package com.example.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.taskmanager.model.projection.ProjectWriteModel;

@Controller
@RequestMapping("/projects")
class ProjectController {
	@GetMapping
	String showProjects(Model model) {
		var projectToEdit = new ProjectWriteModel();
		projectToEdit.setDescription("project to edit");
		model.addAttribute("project", projectToEdit);
		return "projects";
	}
}
