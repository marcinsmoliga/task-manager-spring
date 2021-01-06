package com.example.taskmanager;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskGroup;
import com.example.taskmanager.repository.TaskGroupRepository;

@Component
class WarmUp implements ApplicationListener<ContextRefreshedEvent> {
	public static final Logger logger = LoggerFactory.getLogger(WarmUp.class);
	private final TaskGroupRepository groupRepository;

	WarmUp(TaskGroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		logger.info("Application warmup after context refreshed");

		final String description = "ApplicationContextEvent";
		if(!groupRepository.existsByDescription(description)) {
			logger.info("No required group found! Adding it!");

			var group = new TaskGroup();
			group.setDescription(description);
			group.setTasks(Set.of(
					new Task("ContextClosedEvent", null, group),
					new Task("ContextRefreshedEvent", null, group),
					new Task("ContextStoppedEvent", null, group),
					new Task("ContextStartedEvent", null, group)
			));
			groupRepository.save(group);
		}
	}
}
