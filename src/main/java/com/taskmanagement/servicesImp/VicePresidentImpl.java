package com.taskmanagement.servicesImp;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taskmanagement.entity.Authority;
import com.taskmanagement.entity.Task;
import com.taskmanagement.entity.User;
import com.taskmanagement.repository.AuthorityRepository;
import com.taskmanagement.repository.TaskRepository;
import com.taskmanagement.repository.UserRepository;
import com.taskmanagement.repositoryModel.UserByRoleRepoModel;
import com.taskmanagement.services.VicePresidentServices;

@Service
public class VicePresidentImpl implements VicePresidentServices {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TaskRepository taskRepo;

	@Override
	public ResponseEntity<List<UserByRoleRepoModel>> viewAllManager() {

		List<UserByRoleRepoModel> u = this.userRepo.finduserByRole("MANAGER");

		return new ResponseEntity<List<UserByRoleRepoModel>>(u, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Task>> viewEmployees(String manager) {

		List<Task> EmployeeList = this.taskRepo.findByTaskcreateBy(manager);

		return new ResponseEntity<List<Task>>(EmployeeList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Task>> viewCreateAndAssignByTask() {
		List<Task> usersTask = this.taskRepo.findAll();

		return new ResponseEntity<List<Task>>(usersTask, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> assignTaskTManager(Task[] task) {
		Task isTaskSaved = null;
		
		for (Task TaskAll : task) {
			TaskAll.setCreateBy("ram");
			TaskAll.setUpdateBy("-");
			isTaskSaved = this.taskRepo.save(TaskAll);
		}

		if (isTaskSaved != null) {
			return new ResponseEntity<String>("saved", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("not saved", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Object>> listOfManager() {

		List<Object> listOfManager = this.userRepo.listNameByRole("MANAGER");

		return new ResponseEntity<List<Object>>(listOfManager, HttpStatus.OK);

	}

}
