package com.yb.bean;

public class PartsMaster {
	private String custmer;
	private String custclient;
	private String order;
	private String undersingle;
	private String auditPerson;
	private String PlaceOderdate;
	private String shippingdate;
	private String ExcelServerRCID;
	private String ExcelServerRN;
	private String ExcelServerCN;
	private String ExcelServerRC1;
	private String ExcelServerWIID;
	private String ExcelServerRTID;
	private String ExcelServerCHG;
	
	public PartsMaster() {
		super();
	}
	public PartsMaster(String custmer, String custclient, String order,
			String undersingle, String auditPerson, String placeOderdate,
			String shippingdate, String excelServerRCID, String excelServerRN,
			String excelServerCN, String excelServerRC1, String excelServerWIID,
			String excelServerRTID, String excelServerCHG) {
		super();
		this.custmer = custmer;
		this.custclient = custclient;
		this.order = order;
		this.undersingle = undersingle;
		this.auditPerson = auditPerson;
		PlaceOderdate = placeOderdate;
		this.shippingdate = shippingdate;
		ExcelServerRCID = excelServerRCID;
		ExcelServerRN = excelServerRN;
		ExcelServerCN = excelServerCN;
		ExcelServerRC1 = excelServerRC1;
		ExcelServerWIID = excelServerWIID;
		ExcelServerRTID = excelServerRTID;
		ExcelServerCHG = excelServerCHG;
	}
	public String getCustmer() {
		return custmer;
	}
	public void setCustmer(String custmer) {
		this.custmer = custmer;
	}
	public String getCustclient() {
		return custclient;
	}
	public void setCustclient(String custclient) {
		this.custclient = custclient;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getUndersingle() {
		return undersingle;
	}
	public void setUndersingle(String undersingle) {
		this.undersingle = undersingle;
	}
	public String getAuditPerson() {
		return auditPerson;
	}
	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}
	public String getPlaceOderdate() {
		return PlaceOderdate;
	}
	public void setPlaceOderdate(String placeOderdate) {
		PlaceOderdate = placeOderdate;
	}
	public String getShippingdate() {
		return shippingdate;
	}
	public void setShippingdate(String shippingdate) {
		this.shippingdate = shippingdate;
	}
	public String getExcelServerRCID() {
		return ExcelServerRCID;
	}
	public void setExcelServerRCID(String excelServerRCID) {
		ExcelServerRCID = excelServerRCID;
	}
	public String getExcelServerRN() {
		return ExcelServerRN;
	}
	public void setExcelServerRN(String excelServerRN) {
		ExcelServerRN = excelServerRN;
	}
	public String getExcelServerCN() {
		return ExcelServerCN;
	}
	public void setExcelServerCN(String excelServerCN) {
		ExcelServerCN = excelServerCN;
	}
	public String getExcelServerRC1() {
		return ExcelServerRC1;
	}
	public void setExcelServerRC1(String excelServerRC1) {
		ExcelServerRC1 = excelServerRC1;
	}
	public String getExcelServerWIID() {
		return ExcelServerWIID;
	}
	public void setExcelServerWIID(String excelServerWIID) {
		ExcelServerWIID = excelServerWIID;
	}
	public String getExcelServerRTID() {
		return ExcelServerRTID;
	}
	public void setExcelServerRTID(String excelServerRTID) {
		ExcelServerRTID = excelServerRTID;
	}
	public String getExcelServerCHG() {
		return ExcelServerCHG;
	}
	public void setExcelServerCHG(String excelServerCHG) {
		ExcelServerCHG = excelServerCHG;
	}
	@Override
	public String toString() {
		return "PartsMaster [ExcelServerCHG=" + ExcelServerCHG
				+ ", ExcelServerCN=" + ExcelServerCN + ", ExcelServerRC1="
				+ ExcelServerRC1 + ", ExcelServerRCID=" + ExcelServerRCID
				+ ", ExcelServerRN=" + ExcelServerRN + ", ExcelServerRTID="
				+ ExcelServerRTID + ", ExcelServerWIID=" + ExcelServerWIID
				+ ", PlaceOderdate=" + PlaceOderdate + ", auditPerson="
				+ auditPerson + ", custclient=" + custclient + ", custmer="
				+ custmer + ", order=" + order + ", shippingdate="
				+ shippingdate + ", undersingle=" + undersingle + "]";
	}
	
}
