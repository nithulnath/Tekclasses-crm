package com.dbi.model;

// this class is only for getting the marketing performance details once that query is executed


public class Performance implements Comparable<Performance>{

	private String name;
	private int target;
	private int achieved;
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
	public int getAchieved() {
		return achieved;
	}
	public void setAchieved(int achieved) {
		this.achieved = achieved;
	}
	public Performance(String name, int target, int achieved) {
		super();
		this.name = name;
		this.target = target;
		this.achieved = achieved;
	}
	public Performance() {
		super();
	}
	
	@Override
    public int compareTo(Performance performance) {
        return performance.achieved - this.achieved;
    }
	
	
}
