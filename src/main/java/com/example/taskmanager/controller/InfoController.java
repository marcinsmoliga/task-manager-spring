package com.example.taskmanager.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.TaskConfigurationProperties;

@RestController
@RequestMapping("/info")
class InfoController {

	private final DataSourceProperties dataSource;
	private final TaskConfigurationProperties myProp;

	InfoController(DataSourceProperties dataSource, TaskConfigurationProperties myProp) {
		this.dataSource = dataSource;
		this.myProp = myProp;
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/url")
	String url() {
		return dataSource.getUrl();
	}

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/prop")
	boolean myProp() {
		return myProp.getTemplate().isAllowMultipleTasks();
	}
}
