package com.example.taskmanager.reports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.taskmanager.model.event.TaskDone;
import com.example.taskmanager.model.event.TaskEvent;
import com.example.taskmanager.model.event.TaskUndone;

@Service
class ChangedTaskEventListener {
	public static final Logger logger = LoggerFactory.getLogger(ChangedTaskEventListener.class);
	private final PersistedTaskEventRepository repository;

	ChangedTaskEventListener(PersistedTaskEventRepository repository) {
		this.repository = repository;
	}

	@EventListener
	public void on(TaskDone event) {
		onChanged(event);
	}

	@EventListener
	public void on(TaskUndone event) {
		onChanged(event);
	}

	private void onChanged(TaskEvent event) {
		logger.info("Got " + event);
		repository.save(new PersistedTaskEvent(event));
	}
}
