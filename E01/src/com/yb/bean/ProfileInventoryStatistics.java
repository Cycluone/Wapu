package com.yb.bean;

public class ProfileInventoryStatistics {
	private String model;
	private String textuer;
	private double length;
	private int balance;
	public ProfileInventoryStatistics() {
		super();
	}
	public ProfileInventoryStatistics(String model, String textuer,
			double length, int balance) {
		super();
		this.model = model;
		this.textuer = textuer;
		this.length = length;
		this.balance = balance;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getTextuer() {
		return textuer;
	}
	public void setTextuer(String textuer) {
		this.textuer = textuer;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "ProfileInventoryStatistics [model=" + model + ", textuer="
				+ textuer + ", length=" + length + ", balance=" + balance + "]";
	}
	
	
}
