package com.yb.test;

import java.util.List;

import com.yb.bean.ArrayData1;

public class ListArrayData {
	private String model;
	private List<Double> dobs;
	private ArrayData1 arrdata;
	public ListArrayData(String model, List<Double> dobs,
			ArrayData1 arrdata) {
		super();
		this.model = model;
		this.dobs = dobs;
		this.arrdata = arrdata;
	}
	public ListArrayData() {
		super();
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public List<Double> getDobs() {
		return dobs;
	}
	public void setDobs(List<Double> dobs) {
		this.dobs = dobs;
	}
	public ArrayData1 getArrdata() {
		return arrdata;
	}
	public void setArrdata(ArrayData1 arrdata) {
		this.arrdata = arrdata;
	}
}
