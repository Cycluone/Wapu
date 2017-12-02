package com.yb.bean;

public class Receptacle {
	private String model;//型号
	private double length;//长度
	private int count;//数量
	private int knives;//刀数
	private String scheme;//方案
	private String utilization;//利用率
	private double surplus;//余料
	private double stockLen;//库存长度
	private String materialType;//材料类型
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getKnives() {
		return knives;
	}
	public void setKnives(int knives) {
		this.knives = knives;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	public String getUtilization() {
		return utilization;
	}
	public void setUtilization(String utilization) {
		this.utilization = utilization;
	}
	public double getSurplus() {
		return surplus;
	}
	public void setSurplus(double surplus) {
		this.surplus = surplus;
	}
	
	public double getStockLen() {
		return stockLen;
	}
	public void setStockLen(double stockLen) {
		this.stockLen = stockLen;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	@Override
	public String toString() {
		return "型号=" + model + ",要用库存长度="+stockLen + ", 切割成="	+ scheme  
				 + ", 刀数=" +( knives>1?knives+1:knives)+ ", 利用率="+ utilization+ ", 库存材料切割成零件后剩余的余料=" + ( surplus>100?surplus+"(可入库)":surplus+"(废料)")
				 +", 需要材料数量=" + count +", 使用当前长度库存的类型="+materialType;
	}
	
}
