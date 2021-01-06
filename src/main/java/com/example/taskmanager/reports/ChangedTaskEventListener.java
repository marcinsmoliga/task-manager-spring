package com.example.taskmanager.reports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.taskmanager.model.event.TaskDone;
import com.example.taskmanager.model.event.TaskUndone;

@Service
class ChangedTaskEventListener {
	public static final Logger logger = LoggerFactory.getLogger(ChangedTaskEventListener.class);

	@EventListener
	public void on(TaskDone event) {
		logger.info("Got " + event);
	}

	@EventListener
	public void on(TaskUndone event) {
		logger.info("Got " + event);
	}
}
