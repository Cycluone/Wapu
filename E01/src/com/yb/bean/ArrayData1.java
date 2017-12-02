package com.yb.bean;

import java.util.Arrays;

public class ArrayData1 {
	private double[] InventoryData;
	private int[] InventoryQData;
	private String[] Typedata;
	public ArrayData1(double[] inventoryData, int[] inventoryQData,
			String[] typedata) {
		super();
		InventoryData = inventoryData;
		InventoryQData = inventoryQData;
		Typedata = typedata;
	}
	public ArrayData1() {
		super();
	}
	public double[] getInventoryData() {
		return InventoryData;
	}
	public void setInventoryData(double[] inventoryData) {
		InventoryData = inventoryData;
	}
	public int[] getInventoryQData() {
		return InventoryQData;
	}
	public void setInventoryQData(int[] inventoryQData) {
		InventoryQData = inventoryQData;
	}
	public String[] getTypedata() {
		return Typedata;
	}
	public void setTypedata(String[] typedata) {
		Typedata = typedata;
	}
	@Override
	public String toString() {
		return "ArrayData1 [InventoryData=" + Arrays.toString(InventoryData)
				+ ", InventoryQData=" + Arrays.toString(InventoryQData)
				+ ", Typedata=" + Arrays.toString(Typedata) + "]";
	}
	
}
