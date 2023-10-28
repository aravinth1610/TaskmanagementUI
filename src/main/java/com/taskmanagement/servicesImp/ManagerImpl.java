package com.taskmanagement.servicesImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taskmanagement.entity.Task;
import com.taskmanagement.repository.TaskRepository;
import com.taskmanagement.repository.UserRepository;
import com.taskmanagement.repositoryModel.UserByRoleRepoModel;
import com.taskmanagement.services.ManagerServices;

@Service
public class ManagerImpl implements ManagerServices {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TaskRepository taskRepo;

	@Override
	public ResponseEntity<List<UserByRoleRepoModel>> viewEmployeeDetails() {
		List<UserByRoleRepoModel> u = this.userRepo.finduserByRole("EMPLOYEE");

		return new ResponseEntity<List<UserByRoleRepoModel>>(u, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<String> assignTaskTManager(Task task) {
		task.setCreateBy("ram");
		task.setUpdateBy("-");

		Task isTaskSaved = this.taskRepo.save(task);

		if (isTaskSaved != null) {
			return new ResponseEntity<String>("saved", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("not saved", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Task>> viewCreateAndAssignByTask(String authority) {
		List<Task> usersTask = this.taskRepo.findByAssignRole(authority);

		return new ResponseEntity<List<Task>>(usersTask, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Object>> listOfEmployee() {
		List<Object> listOfManager = this.userRepo.listNameByRole("EMPLOYEE");

		return new ResponseEntity<List<Object>>(listOfManager, HttpStatus.OK);
	}

}
