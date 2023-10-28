package com.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taskmanagement.entity.User;
import com.taskmanagement.repositoryModel.UserByRoleRepoModel;
import com.taskmanagement.sqlConst.SQLConstKeyCode;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = SQLConstKeyCode.findAllByRole, nativeQuery = true)
	List<UserByRoleRepoModel> finduserByRole(String role);
	
	@Query(value = SQLConstKeyCode.listNameByRole, nativeQuery = true)
	List<Object> listNameByRole(String role);
	
	List<User> findByGmail(String gmail);

}
