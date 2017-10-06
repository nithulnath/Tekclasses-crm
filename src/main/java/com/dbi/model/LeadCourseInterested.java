package com.dbi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "leadcourseinterested")
public class LeadCourseInterested implements java.io.Serializable {

	private static final long serialVersionUID = 1L;	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",unique=true, nullable = false)
	private Long id;
	
	@Column(name = "leadId")
	private Long leadId;
	
	@Column(name = "c_id")
	private Long c_id;
	
	@Column(name = "c_name")
	private String c_name;
	
	@Column(name = "mode", length = 20)
	private String mode;
	
	@Column(name = "courseFee")
	private int courseFee;
	
	@Column(name = "feeOffered")
	private int feeOffered;
	
	@Column(name = "feePaid")
	private int feePaid;
	
	@Column(name = "paymentDate")
	private String paymentDate;
	
	@Column(name = "pendingPayment" )
	private int pendingPayment;
	
	@Column(name = "leadStatus", length = 15)
	private String leadStatus;
	
	
	@Column(name = "createdDate",updatable = false,length=20)
	private String createdDate;
	
	@Column(name = "modifiedDate",length=20)
	private String modifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLeadId() {
		return leadId;
	}

	public void setLeadId(Long leadId) {
		this.leadId = leadId;
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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}


	public int getCourseFee() {
		return courseFee;
	}

	public void setCourseFee(int courseFee) {
		this.courseFee = courseFee;
	}

	public int getFeeOffered() {
		return feeOffered;
	}

	public void setFeeOffered(int feeOffered) {
		this.feeOffered = feeOffered;
	}

	public int getFeePaid() {
		return feePaid;
	}

	public void setFeePaid(int feePaid) {
		this.feePaid = feePaid;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getPendingPayment() {
		return pendingPayment;
	}

	public void setPendingPayment(int pendingPayment) {
		this.pendingPayment = pendingPayment;
	}

	
	public String getLeadStatus() {
		return leadStatus;
	}

	public void setLeadStatus(String leadStatus) {
		this.leadStatus = leadStatus;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public LeadCourseInterested() {
		super();
	}

	public LeadCourseInterested(Long id, Long leadId, Long c_id, String c_name,
			String mode, int courseFee, int feeOffered, int feePaid,
			String paymentDate, int pendingPayment, 
			String leadStatus, String createdDate, String modifiedDate) {
		super();
		this.id = id;
		this.leadId = leadId;
		this.c_id = c_id;
		this.c_name = c_name;
		this.mode = mode;
		this.courseFee = courseFee;
		this.feeOffered = feeOffered;
		this.feePaid = feePaid;
		this.paymentDate = paymentDate;
		this.pendingPayment = pendingPayment;
		this.leadStatus = leadStatus;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public LeadCourseInterested(Long leadId, Long c_id, String c_name,
			String mode, int courseFee, int feeOffered, int feePaid,
			String paymentDate, int pendingPayment, 
			String leadStatus, String createdDate, String modifiedDate) {
		super();
		this.leadId = leadId;
		this.c_id = c_id;
		this.c_name = c_name;
		this.mode = mode;
		this.courseFee = courseFee;
		this.feeOffered = feeOffered;
		this.feePaid = feePaid;
		this.paymentDate = paymentDate;
		this.pendingPayment = pendingPayment;
		this.leadStatus = leadStatus;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	
	
}
