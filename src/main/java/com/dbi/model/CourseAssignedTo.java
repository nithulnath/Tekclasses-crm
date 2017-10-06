package com.dbi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courseassignedto")
public class CourseAssignedTo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_id", unique = true, nullable = false)
	private Long cat_id;
	
	@Column(name = "cat_empid")
	private Long cat_empid;
	
	@Column(name = "cat_cid")
	private Long cat_cid;
	
	@Column(name = "cat_cname")
	private String cat_cname;
	
	@Column(name= "cat_createdDate",updatable = false,length=20)
	private String cat_createdDate;

	@Column(name= "cat_modifiedDate")
	private String cat_modifiedDate;

	public Long getCat_id() {
		return cat_id;
	}

	public void setCat_id(Long cat_id) {
		this.cat_id = cat_id;
	}

	public Long getCat_empid() {
		return cat_empid;
	}

	public void setCat_empid(Long cat_empid) {
		this.cat_empid = cat_empid;
	}

	public Long getCat_cid() {
		return cat_cid;
	}

	public void setCat_cid(Long cat_cid) {
		this.cat_cid = cat_cid;
	}

	
	public String getCat_cname() {
		return cat_cname;
	}

	public void setCat_name(String cat_cname) {
		this.cat_cname = cat_cname;
	}

	public String getCat_createdDate() {
		return cat_createdDate;
	}

	public void setCat_createdDate(String cat_createdDate) {
		this.cat_createdDate = cat_createdDate;
	}

	public String getCat_modifiedDate() {
		return cat_modifiedDate;
	}

	public void setCat_modifiedDate(String cat_modifiedDate) {
		this.cat_modifiedDate = cat_modifiedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public CourseAssignedTo(Long cat_empid, Long cat_cid, String cat_cname,
			String cat_createdDate, String cat_modifiedDate) {
		super();
		this.cat_empid = cat_empid;
		this.cat_cid = cat_cid;
		this.cat_cname = cat_cname;
		this.cat_createdDate = cat_createdDate;
		this.cat_modifiedDate = cat_modifiedDate;
	}

	public CourseAssignedTo(Long cat_id, Long cat_empid, Long cat_cid,
			String cat_cname, String cat_createdDate, String cat_modifiedDate) {
		super();
		this.cat_id = cat_id;
		this.cat_empid = cat_empid;
		this.cat_cid = cat_cid;
		this.cat_cname = cat_cname;
		this.cat_createdDate = cat_createdDate;
		this.cat_modifiedDate = cat_modifiedDate;
	}

	public CourseAssignedTo() {
		super();
	}

	
}
