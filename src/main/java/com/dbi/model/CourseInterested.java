package com.dbi.model;

import java.util.List;

public class CourseInterested {
	private Long leadId;
	private Long c_id;
	private String c_name;
	private String mode;
	private int courseFee;
	private int feeOffered;
	private int feePaid;
	private String paymentDate;
	private int pendingPayment;
	private String leadStatus;
	private List<LeadOwner> leadOwner;
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
	public List<LeadOwner> getLeadOwner() {
		return leadOwner;
	}
	public void setLeadOwner(List<LeadOwner> leadOwner) {
		this.leadOwner = leadOwner;
	}
	public CourseInterested(Long leadId, Long c_id, String c_name, String mode,
			int courseFee, int feeOffered, int feePaid, String paymentDate,
			int pendingPayment, String leadStatus, List<LeadOwner> leadOwner) {
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
		this.leadOwner = leadOwner;
	}

	
}
