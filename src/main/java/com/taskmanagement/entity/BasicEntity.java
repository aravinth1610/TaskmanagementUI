package com.taskmanagement.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BasicEntity {

	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="createon")
	private LocalDateTime createOn;
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="updateon")
	private LocalDateTime updateOn;
	@Column(name="createdby")
	private String createBy;
	@Column(name="updateby")
	private String updateBy;
	public LocalDateTime getCreateOn() {
		return createOn;
	}
	public void setCreateOn(LocalDateTime createOn) {
		this.createOn = createOn;
	}
	public LocalDateTime getUpdateOn() {
		return updateOn;
	}
	public void setUpdateOn(LocalDateTime updateOn) {
		this.updateOn = updateOn;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public BasicEntity(LocalDateTime createOn, LocalDateTime updateOn, String createBy, String updateBy) {
		super();
		this.createOn = createOn;
		this.updateOn = updateOn;
		this.createBy = createBy;
		this.updateBy = updateBy;
	}
	public BasicEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
