package com.yb.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class SuperList {//���
	private static Map<Double,SuperA> map;
	public SuperList() {
		
	}
	public SuperList(Map<Double,SuperA> map){
		this.map=map;
	}
	public synchronized void reduceMap(double key){//��������
		 SuperA a = map.get(key);
			 a.setQuantity(a.getQuantity()-1);
			 //System.err.println(a.getQuantity()+"===a.getQuantity()>1"+(a.getQuantity()>1)+"====>"+key);
			 if(a.getQuantity()<1){
				 a= map.remove(key);//������ΪС��1���Ƴ�
				 map=new HashMap<Double, SuperA>(map);
			 }
			// xh();
	}
	public static SuperA getSuperA(double key){//��ȡ��ǰ����������
		return map.get(key);
	}
	public static int getQuantity(double key){ //��ȡ��ǰ����
		SuperA a = map.get(key);
		return a.getQuantity();   
	}
	public static String getType(double key){//��ȡ��ǰ���ȵ�����(������)
		SuperA a = map.get(key);
		return a.getType();  
	}
	public static double getFit(double value){//��ȡ��ǰ��������ʺϵĳ���
		Set<Entry<Double,SuperA>> set = map.entrySet();
		double[] ar=new double[map.size()];
		int i=0;
		for (Entry<Double, SuperA> key : set) {
			ar[i]=key.getKey();
			i++;
		}
		return fit(ar, value);
	}
	public static void xh(){
		Set<Entry<Double,SuperA>> set = map.entrySet();
		for (Entry<Double, SuperA> key : set) {
			 System.out.println(key.getKey()+""+key.getValue().toString());
		}
	}
	// ע��:���ʺ�
	public static double fit(double[] inventoryData, double a) {// 
		double start = inventoryData[0];
		if (inventoryData.length != 0) {
			double st = inventoryData[0] - a;
			for (int i = 0; i < inventoryData.length; i++) {
				if (inventoryData[i] > a) {
					if (st >= (inventoryData[i] - a)) {
						// System.out.println(st+">="+(inventoryData[i]-a)+"="+(st>=(inventoryData[i]-a)));
						start = inventoryData[i];
					}
				}
			}
		}
		return start;
	}
}
