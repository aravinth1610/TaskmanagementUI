package com.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagement.entity.Authority;
import com.taskmanagement.entity.User;

import java.util.List;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {

	
}
