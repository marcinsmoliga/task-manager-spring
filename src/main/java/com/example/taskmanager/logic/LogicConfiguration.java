package com.example.taskmanager.logic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.taskmanager.TaskConfigurationProperties;
import com.example.taskmanager.repository.ProjectRepository;
import com.example.taskmanager.repository.TaskGroupRepository;
import com.example.taskmanager.repository.TaskRepository;

@Configuration
class LogicConfiguration {

	@Bean
	ProjectService projectService(final ProjectRepository repository, final TaskGroupRepository taskGroupRepository, final TaskConfigurationProperties config) {
		return new ProjectService(repository, taskGroupRepository, config);
	}

	@Bean
	TaskGroupService taskGroupService(final TaskGroupRepository taskGroupRepository,
	                                  final TaskRepository taskRepository) {
		return new TaskGroupService(taskGroupRepository, taskRepository);
	}
}
