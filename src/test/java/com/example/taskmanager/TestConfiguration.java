package com.example.taskmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;

@Configuration
class TestConfiguration {

	@Bean
	@Primary
	@Profile("integration")
	TaskRepository testRepo() {
		return new TaskRepository() {
			private Map<Integer,Task> tasks = new HashMap<>();
			@Override
			public List<Task> findAll() {
				return new ArrayList<>(tasks.values());
			}

			@Override
			public Optional<Task> findById(Integer id) {
				return Optional.ofNullable(tasks.get(id));
			}

			@Override
			public Task save(Task entity) {
				return tasks.put(tasks.size() + 1, entity);
			}

			@Override
			public boolean existsById(Integer id) {
				return false;
			}

			@Override
			public boolean existsByDoneIsFalseAndGroup_Id(Integer groupId) {
				return false;
			}

			@Override
			public Page<Task> findAll(Pageable page) {
				return null;
			}

			@Override
			public List<Task> findByDone(boolean done) {
				return null;
			}
		};
	}
}
