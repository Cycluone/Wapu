package com.yb.bean;

public class PartsSlave {
	private String id;
	private String model;
	private String appellation;
	private String material;
	private String color;
	private int height;
	private int width;
	private int thick;
	private String unit;
	private int totalQuantity;
	private double couont;
	private String installationArea;
	private String remark;
	private String species;
	private String ExcelServerRCID;
	private String ExcelServerRN;
	private String ExcelServerCN;
	private String ExcelServerRC1;
	private String ExcelServerWIID;
	private String ExcelServerRTID;
	private String ExcelServerCHG;
	
	public PartsSlave() {
		super();
	}
	
	public PartsSlave(String id, String model, String appellation,
			String material, String color, int height, int width, int thick,
			String unit, int totalQuantity, double couont,
			String installationArea, String remark, String species,
			String excelServerRCID, String excelServerRN, String excelServerCN,
			String excelServerRC1, String excelServerWIID,
			String excelServerRTID, String excelServerCHG) {
		super();
		this.id = id;
		this.model = model;
		this.appellation = appellation;
		this.material = material;
		this.color = color;
		this.height = height;
		this.width = width;
		this.thick = thick;
		this.unit = unit;
		this.totalQuantity = totalQuantity;
		this.couont = couont;
		this.installationArea = installationArea;
		this.remark = remark;
		this.species = species;
		ExcelServerRCID = excelServerRCID;
		ExcelServerRN = excelServerRN;
		ExcelServerCN = excelServerCN;
		ExcelServerRC1 = excelServerRC1;
		ExcelServerWIID = excelServerWIID;
		ExcelServerRTID = excelServerRTID;
		ExcelServerCHG = excelServerCHG;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getAppellation() {
		return appellation;
	}
	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getThick() {
		return thick;
	}
	public void setThick(int thick) {
		this.thick = thick;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getCouont() {
		return couont;
	}
	public void setCouont(double couont) {
		this.couont = couont;
	}
	public String getInstallationArea() {
		return installationArea;
	}
	public void setInstallationArea(String installationArea) {
		this.installationArea = installationArea;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
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
		return "PartsSlave [ExcelServerRCID=" + ExcelServerRCID
				+ ", appellation=" + appellation + ", color=" + color
				+ ", couont=" + couont + ", height=" + height + ", id=" + id
				+ ", installationArea=" + installationArea + ", material="
				+ material + ", model=" + model + ", remark=" + remark
				+ ", species=" + species + ", thick=" + thick
				+ ", totalQuantity=" + totalQuantity + ", unit=" + unit
				+ ", width=" + width + "]";
	}
}
