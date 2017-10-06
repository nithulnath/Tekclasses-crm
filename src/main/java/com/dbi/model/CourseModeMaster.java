package com.dbi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "coursemodemaster")
public class CourseModeMaster implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "m_id", unique = true, nullable = false)
	private Long m_id;
	
	@Column(name = "m_type", length = 50)
	private String m_type;
	
	@Column(name= "m_createdDate",updatable = false,length=20)
	private String m_createdDate;

	@Column(name= "m_modifiedDate")
	private String m_modifiedDate;

	public Long getM_id() {
		return m_id;
	}

	public void setM_id(Long m_id) {
		this.m_id = m_id;
	}

	public String getM_type() {
		return m_type;
	}

	public void setM_type(String m_type) {
		this.m_type = m_type;
	}

	public String getM_createdDate() {
		return m_createdDate;
	}

	public void setM_createdDate(String m_createdDate) {
		this.m_createdDate = m_createdDate;
	}

	public String getM_modifiedDate() {
		return m_modifiedDate;
	}

	public void setM_modifiedDate(String m_modifiedDate) {
		this.m_modifiedDate = m_modifiedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CourseModeMaster(String m_type, String m_createdDate,
			String m_modifiedDate) {
		super();
		this.m_type = m_type;
		this.m_createdDate = m_createdDate;
		this.m_modifiedDate = m_modifiedDate;
	}

	public CourseModeMaster(Long m_id, String m_type, String m_createdDate,
			String m_modifiedDate) {
		super();
		this.m_id = m_id;
		this.m_type = m_type;
		this.m_createdDate = m_createdDate;
		this.m_modifiedDate = m_modifiedDate;
	}

	public CourseModeMaster() {
		super();
	}
	
	
	
	
}
