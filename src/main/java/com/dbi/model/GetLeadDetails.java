package com.dbi.model;

import java.util.List;


public class GetLeadDetails {

	private Long l_id;
	
	private String l_name;
	
	private String l_email;

	private String l_contactNumber;
	
	private String l_country;
	
	private String l_comment;

	private String l_source;
	
	private List<CourseInterested> courseInterested;
	
	private String l_createdDate;
	
	private String l_modifiedDate;

	public Long getL_id() {
		return l_id;
	}

	public void setL_id(Long l_id) {
		this.l_id = l_id;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public String getL_email() {
		return l_email;
	}

	public void setL_email(String l_email) {
		this.l_email = l_email;
	}

	public String getL_contactNumber() {
		return l_contactNumber;
	}

	public void setL_contactNumber(String l_contactNumber) {
		this.l_contactNumber = l_contactNumber;
	}

	public String getL_country() {
		return l_country;
	}

	public void setL_country(String l_country) {
		this.l_country = l_country;
	}


	public String getL_comment() {
		return l_comment;
	}

	public void setL_comment(String l_comment) {
		this.l_comment = l_comment;
	}

	

	

	
	

	public String getL_source() {
		return l_source;
	}

	public void setL_source(String l_source) {
		this.l_source = l_source;
	}

	public List<CourseInterested> getCourseInterested() {
		return courseInterested;
	}

	public void setCourseInterested(List<CourseInterested> courseInterested) {
		this.courseInterested = courseInterested;
	}

	public String getL_createdDate() {
		return l_createdDate;
	}

	public void setL_createdDate(String l_createdDate) {
		this.l_createdDate = l_createdDate;
	}

	public String getL_modifiedDate() {
		return l_modifiedDate;
	}

	public void setL_modifiedDate(String l_modifiedDate) {
		this.l_modifiedDate = l_modifiedDate;
	}

	
	
	
	
	public GetLeadDetails(Long l_id, String l_name, String l_email,
			String l_contactNumber, String l_country, String l_comment,String l_source,
			List<CourseInterested> courseInterested, String l_createdDate,
			String l_modifiedDate) {
		super();
		this.l_id = l_id;
		this.l_name = l_name;
		this.l_email = l_email;
		this.l_contactNumber = l_contactNumber;
		this.l_country = l_country;
		this.l_comment = l_comment;
		this.l_source = l_source;
		this.courseInterested = courseInterested;
		this.l_createdDate = l_createdDate;
		this.l_modifiedDate = l_modifiedDate;
	}

	public GetLeadDetails() {
		super();
	}
	

	

}
