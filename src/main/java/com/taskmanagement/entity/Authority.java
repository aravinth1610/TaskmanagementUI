package com.taskmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tmauthority")
public class Authority {

	@Id
	@Column(name="roleid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	private String role;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Authority(Integer roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}
	public Authority() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
