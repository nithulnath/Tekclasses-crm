package com.dbi.model;

public class IdAndMode {

	
	private int modeid;
	private String mode;
	
	public int getModeid() {
		return modeid;
	}
	public void setModeid(int modeid) {
		this.modeid = modeid;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public IdAndMode(int modeid, String mode) {
		super();
		this.modeid = modeid;
		this.mode = mode;
	}
	public IdAndMode() {
		super();
	}
	
	
	
}
