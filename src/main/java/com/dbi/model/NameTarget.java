package com.dbi.model;

public class NameTarget {
	private String name;
	private int target;
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
	public NameTarget(String name, int target) {
		super();
		this.name = name;
		this.target = target;
	}
	
	

}
