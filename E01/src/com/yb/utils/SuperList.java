package com.yb.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class SuperList {//库存
	private static Map<Double,SuperA> map;
	public SuperList() {
		
	}
	public SuperList(Map<Double,SuperA> map){
		this.map=map;
	}
	public synchronized void reduceMap(double key){//减少数量
		 SuperA a = map.get(key);
			 a.setQuantity(a.getQuantity()-1);
			 //System.err.println(a.getQuantity()+"===a.getQuantity()>1"+(a.getQuantity()>1)+"====>"+key);
			 if(a.getQuantity()<1){
				 a= map.remove(key);//如果库存为小于1就移除
				 map=new HashMap<Double, SuperA>(map);
			 }
			// xh();
	}
	public static SuperA getSuperA(double key){//获取当前数量和类型
		return map.get(key);
	}
	public static int getQuantity(double key){ //获取当前数量
		SuperA a = map.get(key);
		return a.getQuantity();   
	}
	public static String getType(double key){//获取当前长度的类型(正余料)
		SuperA a = map.get(key);
		return a.getType();  
	}
	public static double getFit(double value){//获取当前库存中最适合的长度
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
	// 注释:最适合
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
