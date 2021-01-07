package com.example.taskmanager.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
class SsoController {

	@GetMapping("/logout")
	String logout(HttpServletRequest request) throws Exception {
		request.logout();
		return "index";
	}
}
