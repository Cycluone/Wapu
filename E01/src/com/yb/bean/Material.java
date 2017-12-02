package com.yb.bean;

public class Material {
	private String model;
	private String color;
	private double length;
	private int quantity;
	private String type;
	
	public Material() {
		super();
	}
	public Material(String model, String color, double length, int quantity,
			String type) {
		super();
		this.model = model;
		this.color = color;
		this.length = length;
		this.quantity = quantity;
		this.type = type;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
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
		return "Material [color=" + color + ", length=" + length + ", model="
				+ model + ", quantity=" + quantity + ", type=" + type + "]";
	}
}
