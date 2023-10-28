package com.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagement.entity.Task;
import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

	List<Task> findByTaskcreateBy(String taskcreateBy);
	
	List<Task> findByAssignRole(String assignRole);
	
	
}
