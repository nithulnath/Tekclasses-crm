package com.dbi.model;


public class Course implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long c_id;
	
	private String c_name;
	
    private Long c_modeid;
    
    private int c_courseprice;
	 
    private String c_duration;
	
	private String c_createdDate;
	
	private String c_modifiedDate;

	public Long getC_id() {
		return c_id;
	}

	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public Long getC_modeid() {
		return c_modeid;
	}

	public void setC_modeid(Long c_modeid) {
		this.c_modeid = c_modeid;
	}

	public int getC_courseprice() {
		return c_courseprice;
	}

	public void setC_courseprice(int c_courseprice) {
		this.c_courseprice = c_courseprice;
	}

	public String getC_duration() {
		return c_duration;
	}

	public void setC_duration(String c_duration) {
		this.c_duration = c_duration;
	}

	public String getC_createdDate() {
		return c_createdDate;
	}

	public void setC_createdDate(String c_createdDate) {
		this.c_createdDate = c_createdDate;
	}

	public String getC_modifiedDate() {
		return c_modifiedDate;
	}

	public void setC_modifiedDate(String c_modifiedDate) {
		this.c_modifiedDate = c_modifiedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Course(Long c_id, String c_name, Long c_modeid, int c_courseprice,
			String c_duration, String c_createdDate, String c_modifiedDate) {
		super();
		this.c_id = c_id;
		this.c_name = c_name;
		this.c_modeid = c_modeid;
		this.c_courseprice = c_courseprice;
		this.c_duration = c_duration;
		this.c_createdDate = c_createdDate;
		this.c_modifiedDate = c_modifiedDate;
	}

	public Course() {
		super();
	}

	
}
	
	

	
	
	

	

