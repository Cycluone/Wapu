package com.yb.bean;

import java.util.List;

public class D {
	private String model;
	private List<ArrayData> arraydataAll;
	public D(String model, List<ArrayData> arraydataAll) {
		super();
		this.model = model;
		this.arraydataAll = arraydataAll;
	}
	public D() {
		super();
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public List<ArrayData> getArraydataAll() {
		return arraydataAll;
	}
	public void setArraydataAll(List<ArrayData> arraydataAll) {
		this.arraydataAll = arraydataAll;
	}
	@Override
	public String toString() {
		return "D [arraydataAll=Œﬁ∑®œ‘ æ, model=" + model + "]";
	}
	 
}
