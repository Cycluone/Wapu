package com.yb.bean;

public class ProfileMaterialRemainingStock {
	private String model;
	private String textuer;
	private int length;
	private int balance;
	
	public ProfileMaterialRemainingStock() {
		super();
	}

	public ProfileMaterialRemainingStock(String model, String textuer,
			int length, int balance) {
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
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
		return "ProfileMaterialRemainingStock [model=" + model + ", textuer="
				+ textuer + ", length=" + length + ", balance=" + balance + "]";
	}
	
	
}
