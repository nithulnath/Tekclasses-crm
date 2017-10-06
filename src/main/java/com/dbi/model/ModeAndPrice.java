package com.dbi.model;

public class ModeAndPrice {

	private String mode;
	private int price;
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	public ModeAndPrice(String mode, int price) {
		super();
		this.mode = mode;
		this.price = price;
	}
	public ModeAndPrice() {
		super();
	}
	
	
	
	

}
