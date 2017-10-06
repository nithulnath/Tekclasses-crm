package com.dbi.model;

import java.util.List;


public class AllEmployeeDetails {
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private String role;   
	private int target;
	private List<CourseAssignedTo> courseAssignedto;
	private String createdDate;
	private String modifiedDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
 	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	
	
	
	public List<CourseAssignedTo> getCourseAssignedto() {
		return courseAssignedto;
	}
	public void setCourseAssignedto(List<CourseAssignedTo> courseAssignedto) {
		this.courseAssignedto = courseAssignedto;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public AllEmployeeDetails() {
		super();
	}
	public AllEmployeeDetails(Long id, String name, String email,
			String password, String role, int target,
			List<CourseAssignedTo> list, String createdDate,
			String modifiedDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.target = target;
		this.courseAssignedto = list;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	
	

}
