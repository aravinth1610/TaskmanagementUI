package com.taskmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.entity.Task;
import com.taskmanagement.repositoryModel.UserByRoleRepoModel;
import com.taskmanagement.servicesImp.ManagerImpl;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerImpl managerServ;
	
	@GetMapping("/Managerdetails")
	private final ResponseEntity<List<UserByRoleRepoModel>> ListEmployeeDetails() {
		return this.managerServ.viewEmployeeDetails();
	}
	
	
	@PostMapping("/createTask")
	private final ResponseEntity<String> createTask(@RequestBody Task task) {
		return this.managerServ.assignTaskTManager(task);
	}

	@GetMapping("/viewtasks/{authority}")
	private final ResponseEntity<List<Task>> createViewAndAssignTask(@PathVariable(name="authority") String authority) {
		return this.managerServ.viewCreateAndAssignByTask(authority);
	}
	
	@GetMapping("/employee")
	private final ResponseEntity<List<Object>> listOfMangers() {
		return this.managerServ.listOfEmployee();
	}
	
}
