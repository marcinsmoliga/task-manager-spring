package com.example.taskmanager.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class MvcConfiguration implements WebMvcConfigurer {

	private final Set<HandlerInterceptor> interceptors;

	public MvcConfiguration(Set<HandlerInterceptor> interceptors) {
		this.interceptors = interceptors;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		interceptors.forEach(registry::addInterceptor);
	}
}
