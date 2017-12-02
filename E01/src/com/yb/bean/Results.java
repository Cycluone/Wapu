package com.yb.bean;

public class Results {
	private String model;
	private String scheme;
	private double remaining;
	private int count;
	private double utilization;
	public Results(String model, String scheme, double remaining, int count,
			double utilization) {
		super();
		this.model = model;
		this.scheme = scheme;
		this.remaining = remaining;
		this.count = count;
		this.utilization = utilization;
	}
	public Results() {
		super();
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public double getRemaining() {
		return remaining;
	}
	public void setRemaining(double remaining) {
		this.remaining = remaining;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getUtilization() {
		return utilization;
	}
	public void setUtilization(double utilization) {
		this.utilization = utilization;
	}
	@Override
	public String toString() {
		return "[切割数量=" + count + ", 型号=" + model + ", 剩余材料长度="
				+ remaining + ", 切割方案=" + scheme + ", 利用率="
				+ utilization + "]";
	}
	
}
