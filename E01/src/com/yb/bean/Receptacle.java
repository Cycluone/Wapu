package com.yb.bean;

public class Receptacle {
	private String model;//�ͺ�
	private double length;//����
	private int count;//����
	private int knives;//����
	private String scheme;//����
	private String utilization;//������
	private double surplus;//����
	private double stockLen;//��泤��
	private String materialType;//��������
	
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
		return "�ͺ�=" + model + ",Ҫ�ÿ�泤��="+stockLen + ", �и��="	+ scheme  
				 + ", ����=" +( knives>1?knives+1:knives)+ ", ������="+ utilization+ ", �������и�������ʣ�������=" + ( surplus>100?surplus+"(�����)":surplus+"(����)")
				 +", ��Ҫ��������=" + count +", ʹ�õ�ǰ���ȿ�������="+materialType;
	}
	
}
