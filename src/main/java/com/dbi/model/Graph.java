package com.dbi.model;

import java.util.List;

public class Graph {
	private List<GraphValues> Achieved;
	private List<GraphValues> Target;
	public List<GraphValues> getAchieved() {
		return Achieved;
	}
	public void setAchieved(List<GraphValues> achieved) {
		Achieved = achieved;
	}
	public List<GraphValues> getTarget() {
		return Target;
	}
	public void setTarget(List<GraphValues> target) {
		Target = target;
	}
	public Graph(List<GraphValues> achieved, List<GraphValues> target) {
		super();
		Achieved = achieved;
		Target = target;
	}
	public Graph() {
		super();
	}
	
	

}
