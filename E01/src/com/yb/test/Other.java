package com.yb.test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.yb.bean.ArrayData;
import com.yb.bean.ArrayData1;
import com.yb.bean.B;
import com.yb.bean.D;
import com.yb.bean.IntAll;
import com.yb.bean.Receptacle;
import com.yb.utils.SuperA;
import com.yb.utils.SuperList;

public class Other {
	private static DecimalFormat df = new DecimalFormat("###,###.00");

	public static void main(String[] args) {
		// cc();
		long s = System.currentTimeMillis();
		getfangan();
		long t = System.currentTimeMillis();
		// demos();
		System.out.println("需要时间" + (t - s));

	}
	private static SuperList superList;//库存
	private static void getfangan() {//主方法  生成方案
		List<ListArrayData> capture = SF.getCapture();
		for (int i = 0; i < capture.size(); i++) {
			ListArrayData data = capture.get(i);
			System.out.println("这个"+data.getModel()+"型号要切割成"+Arrays.toString(data.getDobs().toArray()));
			System.out.println("这个"+data.getModel()+"型号的库存"+Arrays.toString(data.getArrdata().getInventoryData())+",库存数量"+Arrays.toString(data.getArrdata().getInventoryQData())+",库存类型"+Arrays.toString(data.getArrdata().getTypedata()));
			xy = data.getDobs();// 注释:需求全局
			if(data.getArrdata().getInventoryData().length!=0){
				superList=new SuperList(rtnKCData(data.getArrdata()));//初始化当前长度的库存
				//System.err.println(superList.getFit(2856.0));
				handling1(data.getModel());
			   //handling(data.getModel(), data.getArrdata());
			}else{
				System.out.println("当前库存没有数据");
			}
			System.out.println("-----");
		}
	}

	/**
	 * @Title: 处理当前型号数据
	 * @param: 注释:当前型号 注释:当前需求 注释:当前库存坯料
	 * @return:
	 * @throws
	 */
	private static List<Receptacle> lrecp = new ArrayList<Receptacle>();
	private static List<Double> xy;
    
	private static void handling(String model, ArrayData1 arrdata) {
		double[] inventoryData = arrdata.getInventoryData();// 当前库存中所有长度
		int[] inventoryQData = arrdata.getInventoryQData();// 当前库存中所有长度的库存
		String[] typeinventory = arrdata.getTypedata();// 当前库存中所有材料的类型(Z:正料
		ArrayList<Double> list = new ArrayList<Double>(xy);
		int i = 0;
		while (true) {
			Receptacle rp = new Receptacle();
			if (i == list.size()) {
				break;
			}
			// System.out.println("运算前"+Arrays.toString(list.toArray()));
			// System.out.println(Arrays.toString(list.toArray()));
			double zhs = zuishihe1(inventoryData, list.get(i));// 库存中最合适的长度(用料后剩余最少)
			superList.reduceMap(zhs);//减去库存
			double zc = list.get(i);
			// System.out.println("当前需要长度:"+list.get(i)+"从库存中查找到的利用率最高的长度是"+zhs);
			int dscount = 1;// 刀数
			double sum = list.get(i);// 当前使用的长度
			shengyu = zhs - list.get(i);// 当前切割后剩余的料 库存料减去需求料
			list.remove(list.get(i));
			// System.out.println(shengyu+"大于有"+MaxCount(list,shengyu));
			List<Double> ds = new ArrayList<Double>();// 当前长度能与剩余的材料一起用
			if (MaxCount(list, shengyu) > 0) {
				ds = huoquokeyong1(list);// 获取可用
				for (int j = 0; j < ds.size(); j++) {
					sum += ds.get(j);// 凡是在需求材料中找到可用的就加上找到的材料长度
					list.remove(ds.get(j));
					dscount++;// 凡是在需求材料中找到可用就多一刀
				}
			}
			// if(!results.containsKey(model)){
			String s = Arrays.toString(ds.toArray()).toString();
			rp.setModel(model);
			rp.setLength(zc);// 当前需要材料
			rp.setCount(1);// 材料数量
			rp.setKnives(dscount);// 切割刀数
			rp.setScheme(zc+("".equals(th(s))?"":"+"+th(s)));// 方案
			rp.setUtilization(df.format((sum / zhs) * 100) + "%" +("".equals(th(s))?"":" ,不加上可用的材料切割后="
					+ (zhs - zc)));// 切割利用率
			rp.setSurplus(zhs - sum);// 剩余材料数
			rp.setStockLen(zhs);// 库存中的长度
			int index = getindex(inventoryData, zhs);// 获取当前库存中当前长度的位置下标 用于查找同样
			if (index != -1) {
				int sl = inventoryQData[index];// 当前长度库存中的数量
				
				String type = typeinventory[index];// 当前材料的类型
				rp.setMaterialType("Z".equals(type) ? "正料" : "余料");
			} else {
				rp.setMaterialType("正料");
			}
			// System.out.println(zhs+"====="+zc+"="+(zhs-zc)+"能与需要的材料一起用的长度"+Arrays.toString(ds.toArray()));
			System.out.println(rp.toString());
			//lrecp.add(rp);
		}
		// System.out.println("型号"+model+"--"+sb.toString());

		// System.out.println(Arrays.toString(inventoryQData));
	}

	// 注释:
	public static void handling1(String model){
		ArrayList<Double> data = new ArrayList<Double>(xy);// 注释:当前需求
		superList.xh();
		while (true) {// 注释:循环当前需求为每一个需求长度寻找库存中最适合的长度
			if(0==data.size()){//当前需求长度全部处理完毕后退出
				break;
			}
			Receptacle rp = new Receptacle();
			Double need = data.get(0);//当前需求长度
			Double sum=data.get(0);//当前总需求长度和
			//System.out.println(need);
			double fit=superList.getFit(need);//获取库存中最合适的长度
			String materialType=superList.getType(fit);
			superList.reduceMap(fit);//减去库存
			int dscount = 1;// 刀数
			shengyu=fit-need;//剩余
			data.remove(data.get(0));// 注释:移除当前需要的长度()
			List<Double> ds = new ArrayList<Double>();// 当前长度能与剩余的材料一起用
			if (MaxCount(data, shengyu) > 0) {
				ds = huoquokeyong1(data);// 获取可用
				for (int j = 0; j < ds.size(); j++) {
					sum += ds.get(j);// 凡是在需求材料中找到可用的就加上找到的材料长度
					data.remove(ds.get(j));
					dscount++;// 凡是在需求材料中找到可用就多一刀
				}
			}
			String arr = Arrays.toString(ds.toArray()).toString();
			//System.err.println(fit+"==="+sum);
			rp.setModel(model);
			rp.setLength(need);// 当前需要材料长度
			rp.setCount(1);// 材料数量
			rp.setKnives(dscount);// 切割刀数
			rp.setScheme(need+("".equals(th(arr))?"":"+"+th(arr)));// 方案
			rp.setUtilization(df.format((need / fit) * 100) + "%" +("".equals(th(arr))?"":" ,不加上可用的材料切割后剩余="
					+ (fit - need)));// 切割利用率
			rp.setSurplus(fit - sum);// 剩余材料数
			rp.setStockLen(fit);// 库存中的长度
			rp.setMaterialType("Z".equals(materialType) ? "正料" : "余料");
			System.out.println(rp.toString());
		}
		superList.xh();
	}
	
	public static double[] zhancun;// 用于暂存
	public static double shengyu;// 暂存剩余材料
	/**
	 *@auther 邓树星
	 *
	 *@注释:获取当前需求材料长度中还能用掉暂存长度的需求长度
	 */
	public static List<Double> huoquokeyong1(ArrayList<Double> list) {
		ArrayList<Double> jssy = new ArrayList<Double>(list);
		/*System.out.println("获取当前需求材料长度中还能用掉暂存长度的需求长度" + shengyu + "--"
				+ Arrays.toString(jssy.toArray()));*/
		List<Double> d = new ArrayList<Double>();
		//double[] zhancun1 = new double[MaxCount(list, shengyu)];
		while (true) {
			if (0 == jssy.size()) {
				break;
			}
			double zhs = zuishihelist(jssy, shengyu);// 库存中最合适的长度(用料后剩余最少)
			if (shengyu > zhs) {// 当前剩余材料要大于需求材料
				shengyu = shengyu - zhs;
				if (shengyu != 0.0) {
					d.add(zhs);
				}
			}
			jssy.remove(zhs);
		}
		return d;
	}
	/**
	 *@auther 邓树星
	 *
	 *@注释: 返回当前列表有多少大于当前值
	 */
	public static int MaxCount(List<Double> list, double value) {
		int count = 0;
		if (list.size() != 0) {
			double st = list.get(0) - value;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) < value) {
					count++;
				}
			}
		}
		return count;
	}
	/**
	 *@auther 邓树星
	 *
	 *@注释:获取当前坯料中长度的位置以便用于确定当前坯料长度的数量库存
*/
	public static int getindex(double[] inventoryData, double value) {// 
		int index = 0;
		for (int i = 0; i < inventoryData.length; i++) {
			if (inventoryData[i] == value) {
				index = i;
			}
		}
		return index;
	}
	/**
	 *@auther 邓树星
	 *
	 *@注释:替换符号([],)
*/
	public static String th(String str) {
		return str.replace("[", "").toString().replace("]", "").replace(",","+");
	}
	/**
	 *@注释:求最适合的长度
	 */
	public static double zuishihe1(double[] inventoryData, double a) {// 
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
	public static double zuishihelist(List<Double> list, double value) {// 获取当前列表中裁剪后最少的料
		/*System.out.println(shengyu + "--"
				+ Arrays.toString(list.toArray()));*/
		double start = list.get(0);
		if (list.size() != 0) {//判断当前列表不为空
			double st = list.get(0) - value;//
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) < value) {
					if (st >= (list.get(i) - value)) {
						start = list.get(i);
					}
				}
			}
		}
		return start;
	}
	public static double zuishiheMap(){
		return 0.0;
	} 
	
	public static double Max(double[] inventoryData) {
		double max = inventoryData[0];
		for (int i = 0; i < inventoryData.length; i++) {
			if (max > inventoryData[i]) {
				max = inventoryData[i];
			}
		}
		return max;
	}
	
	public static int getIndex(int[] demandData, int value) {//获取下标
		if (demandData.length != 0) {
			int lowerBoud = 0;
			int upperBoud = demandData.length - 1;
			int curIn = 0;
			while (true) {
				curIn = (lowerBoud + upperBoud) / 2;
				if (demandData[curIn] == value) {
					return curIn;
				}
			}
		}
		return 0;
	}
	/**
	 *
	 *@注释:返回当前的库存(把库存处理成以长度为key的Map集合   打造成当前当前类型的当前库存)
*/
	public static Map<Double, SuperA> rtnKCData(ArrayData1 arraydata){
		Map<Double, SuperA> map=new HashMap<Double, SuperA>();
		double[] inventoryData = arraydata.getInventoryData();
		for (int i = 0; i < inventoryData.length; i++) {
			SuperA superA=new SuperA(arraydata.getInventoryQData()[i],arraydata.getTypedata()[i]);
			map.put(inventoryData[i], superA);
		}
		return map;
	}

}
