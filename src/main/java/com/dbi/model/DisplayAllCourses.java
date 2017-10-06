package com.dbi.model;

import java.util.List;

public class DisplayAllCourses {
private Long c_id;
private String c_name;
private List<IdAndMode> idandmode;
private List<ModeAndPrice> modeandprice;

private String c_duration;
private List<IdNameEmail> c_employeeNames;
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
public List<IdAndMode> getIdandmode() {
	return idandmode;
}
public void setIdandmode(List<IdAndMode> idandmode) {
	this.idandmode = idandmode;
}
public List<ModeAndPrice> getModeandprice() {
	return modeandprice;
}
public void setModeandprice(List<ModeAndPrice> modeandprice) {
	this.modeandprice = modeandprice;
}
public String getC_duration() {
	return c_duration;
}
public void setC_duration(String c_duration) {
	this.c_duration = c_duration;
}

public List<IdNameEmail> getC_employeeNames() {
	return c_employeeNames;
}
public void setC_employeeNames(List<IdNameEmail> c_employeeNames) {
	this.c_employeeNames = c_employeeNames;
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
public DisplayAllCourses(Long c_id, String c_name, List<IdAndMode> idandmode,
		List<ModeAndPrice> modeandprice, String c_duration,
		List<IdNameEmail> c_employeeNames, String c_createdDate,
		String c_modifiedDate) {
	super();
	this.c_id = c_id;
	this.c_name = c_name;
	this.idandmode = idandmode;
	this.modeandprice = modeandprice;
	this.c_duration = c_duration;
	this.c_employeeNames = c_employeeNames;
	this.c_createdDate = c_createdDate;
	this.c_modifiedDate = c_modifiedDate;
}






}
