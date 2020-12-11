package com.example.taskmanager.model;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
abstract class BaseAuditableEntity {
	private LocalDateTime createdAt;
	private LocalDateTime updatedOn;

	@PrePersist
	void prePersist() {
		createdAt = LocalDateTime.now();
	}

	@PreUpdate
	void preMerge() {
		updatedOn = LocalDateTime.now();
	}
}
