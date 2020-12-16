package com.example.taskmanager.logic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.taskmanager.TaskConfigurationProperties;
import com.example.taskmanager.repository.ProjectRepository;
import com.example.taskmanager.repository.TaskGroupRepository;

class ProjectServiceTest {

	@Test
	@DisplayName("Should throw IllegalStateException when configured to allow just 1 group and the other undone gro1up exists")
	void createGroup_noMultipleGroupsConfig_And_undoneGroupExists_throwsIllegalStateException() {
		// given
		TaskGroupRepository mockGroupRepository = groupRepositoryReturning(true);
		// and
		TaskConfigurationProperties mockConfig = configurationReturning(false);
		// system under test
		var toTest = new ProjectService(null, mockGroupRepository, mockConfig);

		// when
		var exception = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(), 0));

		// then
		assertThat(exception)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("one undone group");
	}

	@Test
	@DisplayName("Should throw IllegalArgumentException when configuration ok and no projects for a given id")
	void createGroup_configurationOK_And_noProjects_throwsIllegalArgumentException() {
		// given
		var mockRepository = mock(ProjectRepository.class);
		when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());
		// and
		TaskConfigurationProperties mockConfig = configurationReturning(true);
		//system under test
		var toTest = new ProjectService(mockRepository, null, mockConfig);

		// when
		var exception = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(), 0));

		// then
		assertThat(exception)
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("id not found");
	}

	@Test
	@DisplayName("Should throw IllegalArgumentException when configured to allow just 1 group and no groups and project")
	void createGroup_noMultipleGroupsConfig_And_noUndoneGroupExists_noProjects_throwsIllegalArgumentException() {
		// given
		var mockRepository = mock(ProjectRepository.class);
		when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());
		// and
		TaskGroupRepository mockGroupRepository = groupRepositoryReturning(false);
		// and
		TaskConfigurationProperties mockConfig = configurationReturning(true);
		// system under test
		var toTest = new ProjectService(mockRepository, mockGroupRepository, mockConfig);

		// when
		var exception = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(), 0));

		// then
		assertThat(exception)
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("id not found");
	}

	private TaskConfigurationProperties configurationReturning(boolean result) {
		var mockTemplate = mock(TaskConfigurationProperties.Template.class);
		when(mockTemplate.isAllowMultipleTasks()).thenReturn(result);
		var mockConfig = mock(TaskConfigurationProperties.class);
		when(mockConfig.getTemplate()).thenReturn(mockTemplate);
		return mockConfig;
	}

	private TaskGroupRepository groupRepositoryReturning(boolean result) {
		var mockGroupRepository = mock(TaskGroupRepository.class);
		when(mockGroupRepository.existsByDoneIsFalseAndProject_Id(anyInt())).thenReturn(result);
		return mockGroupRepository;
	}
}