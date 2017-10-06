package com.dbi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "leadowner")
public class LeadOwner implements java.io.Serializable {

	private static final long serialVersionUID = 1L;	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "l_id",unique=true, nullable = false)
	private Long id;
	
	@Column(name = "courseId")
	private Long courseId;
	
	@Column(name = "leadId")
	private Long leadId;
	
	@Column(name = "ownerId")
	private Long ownerId;
	
	@Column(name = "ownerName")
	private String ownerName;
	
	@Column(name = "ownerEmail")
	private String ownerEmail;
	
	@Column(name = "createdDate")
	private String createdDate;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getLeadId() {
		return leadId;
	}

	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}

	

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	
	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LeadOwner() {
		super();
	}

	public LeadOwner(Long id, Long courseId, Long leadId, Long ownerId,
			String ownerName, String ownerEmail, String createdDate) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.leadId = leadId;
		this.ownerId = ownerId;
		this.ownerName = ownerName;
		this.ownerEmail = ownerEmail;
		this.createdDate = createdDate;
	}

	public LeadOwner(Long courseId, Long leadId, Long ownerId,
			String ownerName, String ownerEmail, String createdDate) {
		super();
		this.courseId = courseId;
		this.leadId = leadId;
		this.ownerId = ownerId;
		this.ownerName = ownerName;
		this.ownerEmail = ownerEmail;
		this.createdDate = createdDate;
	}

	
	
	
	
	
}

