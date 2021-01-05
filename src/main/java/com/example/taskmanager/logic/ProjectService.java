package com.example.taskmanager.logic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.taskmanager.TaskConfigurationProperties;
import com.example.taskmanager.model.Project;
import com.example.taskmanager.model.projection.GroupReadModel;
import com.example.taskmanager.model.projection.GroupTaskWriteModel;
import com.example.taskmanager.model.projection.GroupWriteModel;
import com.example.taskmanager.model.projection.ProjectWriteModel;
import com.example.taskmanager.repository.ProjectRepository;
import com.example.taskmanager.repository.TaskGroupRepository;

public class ProjectService {
	private ProjectRepository repository;
	private TaskGroupRepository taskGroupRepository;
	private TaskGroupService taskGroupService;
	private TaskConfigurationProperties config;

	public ProjectService(final ProjectRepository repository,
	                      final TaskGroupRepository taskGroupRepository,
	                      final TaskGroupService taskGroupService,
	                      final TaskConfigurationProperties config) {
		this.repository = repository;
		this.taskGroupRepository = taskGroupRepository;
		this.taskGroupService = taskGroupService;
		this.config = config;
	}

	public List<Project> readAll() {
		return repository.findAll();
	}

	public Project save(final ProjectWriteModel toSave) {
		return repository.save(toSave.toProject());
	}

	public GroupReadModel createGroup(LocalDateTime deadline, int projectId) {
		if (!config.getTemplate().isAllowMultipleTasks() && taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
			throw new IllegalStateException("Only one undone group from project is allowed");
		}
		GroupReadModel result = repository.findById(projectId)
				.map(project -> {
					var targetGroup = new GroupWriteModel();
					targetGroup.setDescription(project.getDescription());
					targetGroup.setTasks(
							project.getSteps().stream()
									.map(projectStep -> {
										var task = new GroupTaskWriteModel();
										task.setDescription(projectStep.getDescription());
										task.setDeadline(deadline.plusDays(projectStep.getDaysToDeadline()));
										return  task;
									}
									).collect(Collectors.toList())
					);
					return taskGroupService.createGroup(targetGroup, project);
				}).orElseThrow(() -> new IllegalArgumentException("Project with given id not found"));
		return result;
	}
}
