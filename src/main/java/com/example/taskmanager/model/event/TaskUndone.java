package com.example.taskmanager.model.event;

import java.time.Clock;

import com.example.taskmanager.model.Task;

public class TaskUndone extends TaskEvent {
	TaskUndone(Task source) {
		super(source.getId(), Clock.systemDefaultZone());
	}
}
