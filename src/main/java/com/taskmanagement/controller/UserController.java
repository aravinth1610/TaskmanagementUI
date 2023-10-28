package com.taskmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.entity.User;
import com.taskmanagement.servicesImp.UserImpl;
import com.taskmanagement.servicesImp.VicePresidentImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserImpl userServices;

	@PostMapping("/register")
	private final ResponseEntity<String> createUser(@RequestBody User user) {
		return userServices.createUser(user);
	}

	@GetMapping("/login")
	private final ResponseEntity<User> login(Authentication authentication) {
		String username = authentication.getName();

		return userServices.UserAuth(username);
	}

}
