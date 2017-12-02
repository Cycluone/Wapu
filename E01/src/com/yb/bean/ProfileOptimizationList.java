package com.yb.bean;

public class ProfileOptimizationList {
	private String model;
	private String texture;
	private String Partname;
	private String length;
	private String number;
	public ProfileOptimizationList(String model, String texture,
			String partname, String length, String number) {
		super();
		this.model = model;
		this.texture = texture;
		Partname = partname;
		this.length = length;
		this.number = number;
	}
	public ProfileOptimizationList() {
		super();
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getTexture() {
		return texture;
	}
	public void setTexture(String texture) {
		this.texture = texture;
	}
	public String getPartname() {
		return Partname;
	}
	public void setPartname(String partname) {
		Partname = partname;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "ProfileOptimizationList [model=" + model + ", texture="
				+ texture + ", Partname=" + Partname + ", length=" + length
				+ ", number=" + number + "]";
	}
	
	
	
	
}
