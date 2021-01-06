package com.example.taskmanager.model.event;

import java.time.Clock;

import com.example.taskmanager.model.Task;

class TaskUndone extends TaskEvent {
	public TaskUndone(Task source) {
		super(source.getId(), Clock.systemDefaultZone());
	}
}
