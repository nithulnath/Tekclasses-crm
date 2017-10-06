package com.dbi.model;

public class EmailNameTarget {
	
	private String email;
	private String name;
	private int target;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public EmailNameTarget(String email, String name, int target) {
		super();
		this.email = email;
		this.name = name;
		this.target = target;
	}
	public EmailNameTarget() {
		super();
	}
	
	

}
