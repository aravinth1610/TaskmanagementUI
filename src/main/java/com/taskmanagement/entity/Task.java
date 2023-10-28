package com.taskmanagement.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tmtask")
public class Task extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taskid")
	private Integer taskId;
	@Column(name = "taskdesc")
	private String taskDesc;
	@Column(name = "taskcreatedby")
	private String taskcreateBy;
	@Column(name = "assignto")
	private String assignTo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
	@Column(name = "starton")
	private Date startOn;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
	@Column(name = "endon")
	private Date endOn;
	@Column(name = "assignrole")
	private String assignRole;
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public String getTaskcreateBy() {
		return taskcreateBy;
	}
	public void setTaskcreateBy(String taskcreateBy) {
		this.taskcreateBy = taskcreateBy;
	}
	public String getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}
	public Date getStartOn() {
		return startOn;
	}
	public void setStartOn(Date startOn) {
		this.startOn = startOn;
	}
	public Date getEndOn() {
		return endOn;
	}
	public void setEndOn(Date endOn) {
		this.endOn = endOn;
	}
	public String getAssignRole() {
		return assignRole;
	}
	public void setAssignRole(String assignRole) {
		this.assignRole = assignRole;
	}
	public Task(LocalDateTime createOn, LocalDateTime updateOn, String createBy, String updateBy, Integer taskId,
			String taskDesc, String taskcreateBy, String assignTo, Date startOn, Date endOn, String assignRole) {
		super(createOn, updateOn, createBy, updateBy);
		this.taskId = taskId;
		this.taskDesc = taskDesc;
		this.taskcreateBy = taskcreateBy;
		this.assignTo = assignTo;
		this.startOn = startOn;
		this.endOn = endOn;
		this.assignRole = assignRole;
	}
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Task(LocalDateTime createOn, LocalDateTime updateOn, String createBy, String updateBy) {
		super(createOn, updateOn, createBy, updateBy);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
