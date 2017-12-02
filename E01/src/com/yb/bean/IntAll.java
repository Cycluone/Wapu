package com.yb.bean;

public class IntAll {
	private int length;
	private int quantity;
	public IntAll(int length, int quantity) {
		super();
		this.length = length;
		this.quantity = quantity;
	}
	public IntAll() {
		super();
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "IntAll [length=" + length + ", quantity=" + quantity + "]";
	}
	
}
