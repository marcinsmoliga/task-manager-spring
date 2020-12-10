package com.example.taskmanager;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties("task")
public class TaskConfigurationProperties {

	private boolean allowMultipleTasksFromTemplate;

	public boolean isAllowMultipleTasksFromTemplate() {
		return allowMultipleTasksFromTemplate;
	}

	public void setAllowMultipleTasksFromTemplate(boolean allowMultipleTasksFromTemplate) {
		this.allowMultipleTasksFromTemplate = allowMultipleTasksFromTemplate;
	}
}
