package com.dbi.model;

public class ToGetCourseDetails {
	
	// to get course details using @Query
	
	private Long modeid;
	private int price;
	private String duration;
	public Long getModeid() {
		return modeid;
	}
	public void setModeid(Long modeid) {
		this.modeid = modeid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public ToGetCourseDetails(Long modeid, int price, String duration) {
		super();
		this.modeid = modeid;
		this.price = price;
		this.duration = duration;
	}
	public ToGetCourseDetails() {
		super();
	}
	
		
	

}
