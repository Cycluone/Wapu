package com.yb.bean;

import java.util.Arrays;

public class ArrayData {
	private int[] DemandData;//=new int[intAll.size()];
	private int[] DemandQData;//=new int[intAll.size()];
	private double[] InventoryData;
	private int[] InventoryQData;
	private String[] Typedata;
	public ArrayData(int[] demandData, int[] demandQData,
			double[] inventoryData, int[] inventoryQData, String[] typedata) {
		super();
		DemandData = demandData;
		DemandQData = demandQData;
		InventoryData = inventoryData;
		InventoryQData = inventoryQData;
		Typedata = typedata;
	}
	public ArrayData() {
		super();
	}
	public int[] getDemandData() {
		return DemandData;
	}
	public void setDemandData(int[] demandData) {
		DemandData = demandData;
	}
	public int[] getDemandQData() {
		return DemandQData;
	}
	public void setDemandQData(int[] demandQData) {
		DemandQData = demandQData;
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
		return "ArrayData [DemandData=" + Arrays.toString(DemandData)
				+ ", DemandQData=" + Arrays.toString(DemandQData)
				+ ", InventoryData=" + Arrays.toString(InventoryData)
				+ ", InventoryQData=" + Arrays.toString(InventoryQData)
				+ ", Typedata=" + Arrays.toString(Typedata) + "]";
	} 
}
