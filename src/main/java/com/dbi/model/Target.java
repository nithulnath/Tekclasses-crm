package com.dbi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


// this class is for saving the Target details(which keeps changing every month)

@Entity
@Table(name = "target")
public class Target implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "t_id", unique = true, nullable = false)
	private Long t_id;
	
	@Column(name = "t_empid")
	private Long t_empid;
	
	@Column(name = "t_empname", length = 50)
	private String t_empname;
	
	@Column(name = "t_empemail", length = 50)
	private String t_empemail;
	
	@Column(name = "t_emptarget")
	private int t_emptarget;
	
	@Column(name = "t_emprole", length = 50)
	private String t_emprole;

	@Column(name = "t_createdDate",updatable = false,length=20)
	private String t_createdDate;

	public Long getT_id() {
		return t_id;
	}

	public void setT_id(Long t_id) {
		this.t_id = t_id;
	}
	



	public Long getT_empid() {
		return t_empid;
	}

	public void setT_empid(Long t_empid) {
		this.t_empid = t_empid;
	}

	
	public String getT_createdDate() {
		return t_createdDate;
	}

	public void setT_createdDate(String t_createdDate) {
		this.t_createdDate = t_createdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Target() {
		super();
	}

	public Target(Long t_id) {
		super();
		this.t_id = t_id;
	}

	public String getT_empname() {
		return t_empname;
	}

	public void setT_empname(String t_empname) {
		this.t_empname = t_empname;
	}

	public String getT_empemail() {
		return t_empemail;
	}

	public void setT_empemail(String t_empemail) {
		this.t_empemail = t_empemail;
	}


	public int getT_emptarget() {
		return t_emptarget;
	}

	public void setT_emptarget(int t_emptarget) {
		this.t_emptarget = t_emptarget;
	}

	public String getT_emprole() {
		return t_emprole;
	}

	public void setT_emprole(String t_emprole) {
		this.t_emprole = t_emprole;
	}

	public Target(Long t_empid, String t_empname, String t_empemail,
			 int t_emptarget,
			String t_emprole, String t_createdDate) {
		super();
		this.t_empid = t_empid;
		this.t_empname = t_empname;
		this.t_empemail = t_empemail;
		this.t_emptarget = t_emptarget;
		this.t_emprole = t_emprole;
		this.t_createdDate = t_createdDate;
	}

	public Target(Long t_id, Long t_empid, String t_empname, String t_empemail,
			int t_emptarget,
			String t_emprole, String t_createdDate) {
		super();
		this.t_id = t_id;
		this.t_empid = t_empid;
		this.t_empname = t_empname;
		this.t_empemail = t_empemail;
		this.t_emptarget = t_emptarget;
		this.t_emprole = t_emprole;
		this.t_createdDate = t_createdDate;
	}

	
	
	
	
}
