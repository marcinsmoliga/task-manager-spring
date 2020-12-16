package com.example.taskmanager.logic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.taskmanager.TaskConfigurationProperties;
import com.example.taskmanager.repository.TaskGroupRepository;

class ProjectServiceTest {

	@Test
	@DisplayName("Should throw IllegalStateException when configured to allow just 1 group and the other undone gro1up exists")
	void createGroup_noMultipleGroupsConfig_And_undoneGroupExists_throwsIllegalStateException() {
		//given
		var mockGroupRepository = mock(TaskGroupRepository.class);
		when(mockGroupRepository.existsByDoneIsFalseAndProject_Id(anyInt())).thenReturn(true);
		//and
		var mockTemplate = mock(TaskConfigurationProperties.Template.class);
		when(mockTemplate.isAllowMultipleTasks()).thenReturn(false);
		//and
		var mockConfig = mock(TaskConfigurationProperties.class);
		when(mockConfig.getTemplate()).thenReturn(mockTemplate);
		//system under test
		var toTest = new ProjectService(null, mockGroupRepository, mockConfig);

		//when
		toTest.createGroup(LocalDateTime.now(), 0);
	}
}