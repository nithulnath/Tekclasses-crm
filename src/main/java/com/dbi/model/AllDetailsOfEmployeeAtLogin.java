package com.dbi.model;

import java.util.List;

public class AllDetailsOfEmployeeAtLogin {
	
private AllEmployeeDetails allemployeedetails;
private List<GetLeadDetails> getleaddetails;

public AllEmployeeDetails getAllemployeedetails() {
	return allemployeedetails;
}
public void setAllemployeedetails(AllEmployeeDetails allemployeedetails) {
	this.allemployeedetails = allemployeedetails;
}
public List<GetLeadDetails> getGetleaddetails() {
	return getleaddetails;
}
public void setGetleaddetails(List<GetLeadDetails> getleaddetails) {
	this.getleaddetails = getleaddetails;
}
public AllDetailsOfEmployeeAtLogin(AllEmployeeDetails allemployeedetails,
		List<GetLeadDetails> getleaddetails) {
	super();
	this.allemployeedetails = allemployeedetails;
	this.getleaddetails = getleaddetails;
}
public AllDetailsOfEmployeeAtLogin() {
	super();
}

}
