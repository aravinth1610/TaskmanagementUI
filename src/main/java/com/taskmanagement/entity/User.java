package com.taskmanagement.entity;


import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tmuser")
public class User extends BasicEntity {

	@Id
	@Column(name = "userid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String name;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String gmail;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "authorityfk", referencedColumnName = "userid")
	private Set<Authority> authority;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public Set<Authority> getAuthority() {
		return authority;
	}

	public void setAuthority(Set<Authority> authority) {
		this.authority = authority;
	}

	public User(LocalDateTime createOn, LocalDateTime updateOn, String createBy, String updateBy, Integer userId, String name,
			String password, String gmail, Set<Authority> authority) {
		super(createOn, updateOn, createBy, updateBy);
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.gmail = gmail;
		this.authority = authority;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(LocalDateTime createOn, LocalDateTime updateOn, String createBy, String updateBy) {
		super(createOn, updateOn, createBy, updateBy);
		// TODO Auto-generated constructor stub
	}

}
