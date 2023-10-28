package com.taskmanagement.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.taskmanagement.entity.Task;
import com.taskmanagement.repositoryModel.UserByRoleRepoModel;

public abstract interface ManagerServices {

	abstract ResponseEntity<List<UserByRoleRepoModel>> viewEmployeeDetails();
	abstract ResponseEntity<String> assignTaskTManager(Task task);
	abstract ResponseEntity<List<Task>> viewCreateAndAssignByTask(String authority);
	abstract ResponseEntity<List<Object>> listOfEmployee();
	

}
