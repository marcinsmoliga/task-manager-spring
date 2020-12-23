package com.example.taskmanager.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class LoggerFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(LoggerFilter.class);

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
	                     FilterChain filterChain) throws IOException, ServletException {

		if (servletRequest instanceof HttpServletRequest) {
			var httpRequest = (HttpServletRequest) servletRequest;
			logger.info("[doFilter]" + httpRequest.getMethod() + " " + httpRequest.getRequestURI());
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
}
