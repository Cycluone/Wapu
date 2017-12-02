package com.yb.bean;

import java.util.Comparator;

public class B implements Comparator {
	private Double utilization;
	private String scheme;
	private double require;
	
	@Override
	public String toString() {
		return "B [require=" + require + ", scheme=" + scheme
				+ ", utilization=" + utilization + "]";
	}

	public double getRequire() {
		return require;
	}

	public void setRequire(double require) {
		this.require = require;
	}

	public Double getUtilization() {
		return utilization;
	}

	public void setUtilization(Double utilization) {
		this.utilization = utilization;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	 public int compare(Object object1, Object object2) {// 实现接口中的方法  
	        B p1 = (B) object1; // 强制转换  
	        B p2 = (B) object2;  
	        return new Double(p1.getUtilization()).compareTo(new Double(p2.getUtilization()));  
	    }  
	
}
 
