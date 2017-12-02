package com.yb.bean;

public class CoreProfile {
	private String orderid;
	private String Customer;
	private String ExcelServerRCID;
	public CoreProfile(String orderid, String customer, String excelServerRCID) {
		super();
		this.orderid = orderid;
		Customer = customer;
		ExcelServerRCID = excelServerRCID;
	}
	public CoreProfile() {
		super();
	}
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getCustomer() {
		return Customer;
	}
	public void setCustomer(String customer) {
		Customer = customer;
	}
	public String getExcelServerRCID() {
		return ExcelServerRCID;
	}
	public void setExcelServerRCID(String excelServerRCID) {
		ExcelServerRCID = excelServerRCID;
	}
	@Override
	public String toString() {
		return "CoreProfile [orderid=" + orderid + ", Customer=" + Customer
				+ ", ExcelServerRCID=" + ExcelServerRCID + "]";
	}
	
	
	
}
