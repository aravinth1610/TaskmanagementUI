package com.taskmanagement.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.taskmanagement.entity.Task;
import com.taskmanagement.repositoryModel.UserByRoleRepoModel;

public abstract interface VicePresidentServices {

	abstract ResponseEntity<List<UserByRoleRepoModel>> viewAllManager();
	abstract ResponseEntity<List<Task>> viewEmployees(String manager);
	abstract ResponseEntity<List<Task>> viewCreateAndAssignByTask();
	abstract ResponseEntity<String> assignTaskTManager(Task[] task);
	abstract ResponseEntity<List<Object>> listOfManager();
	
	
}
