package com.dbi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "allcoursedetails")
public class AllCourseDetails implements java.io.Serializable {

	private static final long serialVersionUID = 1L;	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "c_id")
	private Long c_id;
	
	@Column(name = "c_name", length = 50)
	private String c_name;
	
	@Column(name = "c_mode")
	private String[] c_mode;
	
	@Column(name = "c_price")
	private int[] c_price;
	
	@Column(name = "c_duration")
	private String c_duration;
	
	@Column(name = "c_createdDate",updatable = false)
	private String c_createdDate;
	
	
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

	@Column(name = "c_modifiedDate")
	private String c_modifiedDate;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String[] getC_mode() {
		return c_mode;
	}

	public void setC_mode(String[] c_mode) {
		this.c_mode = c_mode;
	}

	public int[] getC_price() {
		return c_price;
	}

	public void setC_price(int[] c_price) {
		this.c_price = c_price;
	}

	public String getC_duration() {
		return c_duration;
	}

	public void setC_duration(String c_duration) {
		this.c_duration = c_duration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	public AllCourseDetails(Long c_id, String c_name, String c_duration,
			String c_createdDate, String c_modifiedDate) {
		super();
		this.c_id = c_id;
		this.c_name = c_name;
		this.c_duration = c_duration;
		this.c_createdDate = c_createdDate;
		this.c_modifiedDate = c_modifiedDate;
	}

	public AllCourseDetails(Long c_id, String c_name, String[] c_mode,
			int[] c_price, String c_duration, String c_createdDate,
			String c_modifiedDate) {
		super();
		this.c_id = c_id;
		this.c_name = c_name;
		this.c_mode = c_mode;
		this.c_price = c_price;
		this.c_duration = c_duration;
		this.c_createdDate = c_createdDate;
		this.c_modifiedDate = c_modifiedDate;
	}

	public AllCourseDetails(Long id, Long c_id, String c_name, String[] c_mode,
			int[] c_price, String c_duration, String c_createdDate,
			String c_modifiedDate) {
		super();
		this.id = id;
		this.c_id = c_id;
		this.c_name = c_name;
		this.c_mode = c_mode;
		this.c_price = c_price;
		this.c_duration = c_duration;
		this.c_createdDate = c_createdDate;
		this.c_modifiedDate = c_modifiedDate;
	}

	public AllCourseDetails() {
		super();
	}
	
	
	

}
