package com.example.taskmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;

@Configuration
class TestConfiguration {

	@Bean
	@Primary
	@Profile("!integration")
	DataSource e2eTestDataSource() {
		var result = new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
		result.setDriverClassName("org.h2.Driver");
		return  result;
	}

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
				int key = tasks.size() + 1;
				try {
					var field = Task.class.getDeclaredField("id");
					field.setAccessible(true);
					field.set(entity, key);
				} catch (NoSuchFieldException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
				tasks.put(key, entity);
				return tasks.get(key);
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

			@Override
			public List<Task> findAllByGroup_Id(Integer groupId) {
				return List.of();
			}
		};
	}
}
