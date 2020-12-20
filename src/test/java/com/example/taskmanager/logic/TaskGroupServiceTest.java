package com.example.taskmanager.logic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.taskmanager.repository.TaskRepository;

class TaskGroupServiceTest {

	@Test
	@DisplayName("Should throw when undone tasks")
	void toggleGroup_undoneTasks_throwsIllegalStateException() {
		// given
		var mockTaskRepository = mock(TaskRepository.class);
		when(mockTaskRepository.existsByDoneIsFalseAndGroup_Id(anyInt())).thenReturn(true);
		// system under test
		var toTest = new TaskGroupService(null, mockTaskRepository);

		// when
		var exception = catchThrowable(() -> toTest.toggleGroup(1));

		// then
		assertThat(exception)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("undone tasks");
	}
}
