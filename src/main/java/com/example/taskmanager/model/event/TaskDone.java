package com.example.taskmanager.model.event;

import java.time.Clock;

import com.example.taskmanager.model.Task;

class TaskDone extends TaskEvent {
	public TaskDone(Task source) {
		super(source.getId(), Clock.systemDefaultZone());
	}
}
