package com.taskmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.entity.Task;
import com.taskmanagement.repository.TaskRepository;
import com.taskmanagement.repositoryModel.UserByRoleRepoModel;
import com.taskmanagement.servicesImp.VicePresidentImpl;


@RestController
@RequestMapping("/vice")
public class VicePresidentController {

	@Autowired
	private VicePresidentImpl viceSer;

	@GetMapping("/Managerdetails")
	private final ResponseEntity<List<UserByRoleRepoModel>> ListManagerDetails() {
		return this.viceSer.viewAllManager();
	}

	@GetMapping("/emplydetails/{manager}")
	private final ResponseEntity<List<Task>> ListEmployeeDetails(@PathVariable(name = "manager") String manager) {
		return this.viceSer.viewEmployees(manager);
	}

	@PostMapping("/createTask")
	private final ResponseEntity<String> createTask(@RequestBody Task[] task) {
		System.out.println(task);
		return this.viceSer.assignTaskTManager(task);
	}

	@GetMapping("/viewtasks")
	private final ResponseEntity<List<Task>> createViewAndAssignTask() {
		return this.viceSer.viewCreateAndAssignByTask();
	}

	@GetMapping("/managers")
	private final ResponseEntity<List<Object>> listOfMangers() {
		return this.viceSer.listOfManager();
	}

}
