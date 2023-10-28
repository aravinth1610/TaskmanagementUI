package com.taskmanagement.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.taskmanagement.entity.User;

public abstract interface UserServices {

	abstract ResponseEntity<?> createUser(User user);
	abstract ResponseEntity<User> UserAuth(String username);
	
}
