package com.yb.utils;

public class SuperA {
	private int quantity;//����
	private String type;//����
	
	public SuperA() {
		super();
	}
	public SuperA(int quantity, String type) {
		super();
		this.quantity = quantity;
		this.type = type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "SuperA [quantity=" + quantity + ", type=" + ("Z".equals(type) ? "����" : "����" )+ "]";
	}
	
}
