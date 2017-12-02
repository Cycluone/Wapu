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
		System.out.println("��Ҫʱ��" + (t - s));

	}
	private static SuperList superList;//���
	private static void getfangan() {//������  ���ɷ���
		List<ListArrayData> capture = SF.getCapture();
		for (int i = 0; i < capture.size(); i++) {
			ListArrayData data = capture.get(i);
			System.out.println("���"+data.getModel()+"�ͺ�Ҫ�и��"+Arrays.toString(data.getDobs().toArray()));
			System.out.println("���"+data.getModel()+"�ͺŵĿ��"+Arrays.toString(data.getArrdata().getInventoryData())+",�������"+Arrays.toString(data.getArrdata().getInventoryQData())+",�������"+Arrays.toString(data.getArrdata().getTypedata()));
			xy = data.getDobs();// ע��:����ȫ��
			if(data.getArrdata().getInventoryData().length!=0){
				superList=new SuperList(rtnKCData(data.getArrdata()));//��ʼ����ǰ���ȵĿ��
				//System.err.println(superList.getFit(2856.0));
				handling1(data.getModel());
			   //handling(data.getModel(), data.getArrdata());
			}else{
				System.out.println("��ǰ���û������");
			}
			System.out.println("-----");
		}
	}

	/**
	 * @Title: ����ǰ�ͺ�����
	 * @param: ע��:��ǰ�ͺ� ע��:��ǰ���� ע��:��ǰ�������
	 * @return:
	 * @throws
	 */
	private static List<Receptacle> lrecp = new ArrayList<Receptacle>();
	private static List<Double> xy;
    
	private static void handling(String model, ArrayData1 arrdata) {
		double[] inventoryData = arrdata.getInventoryData();// ��ǰ��������г���
		int[] inventoryQData = arrdata.getInventoryQData();// ��ǰ��������г��ȵĿ��
		String[] typeinventory = arrdata.getTypedata();// ��ǰ��������в��ϵ�����(Z:����
		ArrayList<Double> list = new ArrayList<Double>(xy);
		int i = 0;
		while (true) {
			Receptacle rp = new Receptacle();
			if (i == list.size()) {
				break;
			}
			// System.out.println("����ǰ"+Arrays.toString(list.toArray()));
			// System.out.println(Arrays.toString(list.toArray()));
			double zhs = zuishihe1(inventoryData, list.get(i));// ���������ʵĳ���(���Ϻ�ʣ������)
			superList.reduceMap(zhs);//��ȥ���
			double zc = list.get(i);
			// System.out.println("��ǰ��Ҫ����:"+list.get(i)+"�ӿ���в��ҵ�����������ߵĳ�����"+zhs);
			int dscount = 1;// ����
			double sum = list.get(i);// ��ǰʹ�õĳ���
			shengyu = zhs - list.get(i);// ��ǰ�и��ʣ����� ����ϼ�ȥ������
			list.remove(list.get(i));
			// System.out.println(shengyu+"������"+MaxCount(list,shengyu));
			List<Double> ds = new ArrayList<Double>();// ��ǰ��������ʣ��Ĳ���һ����
			if (MaxCount(list, shengyu) > 0) {
				ds = huoquokeyong1(list);// ��ȡ����
				for (int j = 0; j < ds.size(); j++) {
					sum += ds.get(j);// ����������������ҵ����õľͼ����ҵ��Ĳ��ϳ���
					list.remove(ds.get(j));
					dscount++;// ����������������ҵ����þͶ�һ��
				}
			}
			// if(!results.containsKey(model)){
			String s = Arrays.toString(ds.toArray()).toString();
			rp.setModel(model);
			rp.setLength(zc);// ��ǰ��Ҫ����
			rp.setCount(1);// ��������
			rp.setKnives(dscount);// �и��
			rp.setScheme(zc+("".equals(th(s))?"":"+"+th(s)));// ����
			rp.setUtilization(df.format((sum / zhs) * 100) + "%" +("".equals(th(s))?"":" ,�����Ͽ��õĲ����и��="
					+ (zhs - zc)));// �и�������
			rp.setSurplus(zhs - sum);// ʣ�������
			rp.setStockLen(zhs);// ����еĳ���
			int index = getindex(inventoryData, zhs);// ��ȡ��ǰ����е�ǰ���ȵ�λ���±� ���ڲ���ͬ��
			if (index != -1) {
				int sl = inventoryQData[index];// ��ǰ���ȿ���е�����
				
				String type = typeinventory[index];// ��ǰ���ϵ�����
				rp.setMaterialType("Z".equals(type) ? "����" : "����");
			} else {
				rp.setMaterialType("����");
			}
			// System.out.println(zhs+"====="+zc+"="+(zhs-zc)+"������Ҫ�Ĳ���һ���õĳ���"+Arrays.toString(ds.toArray()));
			System.out.println(rp.toString());
			//lrecp.add(rp);
		}
		// System.out.println("�ͺ�"+model+"--"+sb.toString());

		// System.out.println(Arrays.toString(inventoryQData));
	}

	// ע��:
	public static void handling1(String model){
		ArrayList<Double> data = new ArrayList<Double>(xy);// ע��:��ǰ����
		superList.xh();
		while (true) {// ע��:ѭ����ǰ����Ϊÿһ�����󳤶�Ѱ�ҿ�������ʺϵĳ���
			if(0==data.size()){//��ǰ���󳤶�ȫ��������Ϻ��˳�
				break;
			}
			Receptacle rp = new Receptacle();
			Double need = data.get(0);//��ǰ���󳤶�
			Double sum=data.get(0);//��ǰ�����󳤶Ⱥ�
			//System.out.println(need);
			double fit=superList.getFit(need);//��ȡ���������ʵĳ���
			String materialType=superList.getType(fit);
			superList.reduceMap(fit);//��ȥ���
			int dscount = 1;// ����
			shengyu=fit-need;//ʣ��
			data.remove(data.get(0));// ע��:�Ƴ���ǰ��Ҫ�ĳ���()
			List<Double> ds = new ArrayList<Double>();// ��ǰ��������ʣ��Ĳ���һ����
			if (MaxCount(data, shengyu) > 0) {
				ds = huoquokeyong1(data);// ��ȡ����
				for (int j = 0; j < ds.size(); j++) {
					sum += ds.get(j);// ����������������ҵ����õľͼ����ҵ��Ĳ��ϳ���
					data.remove(ds.get(j));
					dscount++;// ����������������ҵ����þͶ�һ��
				}
			}
			String arr = Arrays.toString(ds.toArray()).toString();
			//System.err.println(fit+"==="+sum);
			rp.setModel(model);
			rp.setLength(need);// ��ǰ��Ҫ���ϳ���
			rp.setCount(1);// ��������
			rp.setKnives(dscount);// �и��
			rp.setScheme(need+("".equals(th(arr))?"":"+"+th(arr)));// ����
			rp.setUtilization(df.format((need / fit) * 100) + "%" +("".equals(th(arr))?"":" ,�����Ͽ��õĲ����и��ʣ��="
					+ (fit - need)));// �и�������
			rp.setSurplus(fit - sum);// ʣ�������
			rp.setStockLen(fit);// ����еĳ���
			rp.setMaterialType("Z".equals(materialType) ? "����" : "����");
			System.out.println(rp.toString());
		}
		superList.xh();
	}
	
	public static double[] zhancun;// �����ݴ�
	public static double shengyu;// �ݴ�ʣ�����
	/**
	 *@auther ������
	 *
	 *@ע��:��ȡ��ǰ������ϳ����л����õ��ݴ泤�ȵ����󳤶�
	 */
	public static List<Double> huoquokeyong1(ArrayList<Double> list) {
		ArrayList<Double> jssy = new ArrayList<Double>(list);
		/*System.out.println("��ȡ��ǰ������ϳ����л����õ��ݴ泤�ȵ����󳤶�" + shengyu + "--"
				+ Arrays.toString(jssy.toArray()));*/
		List<Double> d = new ArrayList<Double>();
		//double[] zhancun1 = new double[MaxCount(list, shengyu)];
		while (true) {
			if (0 == jssy.size()) {
				break;
			}
			double zhs = zuishihelist(jssy, shengyu);// ���������ʵĳ���(���Ϻ�ʣ������)
			if (shengyu > zhs) {// ��ǰʣ�����Ҫ�����������
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
	 *@auther ������
	 *
	 *@ע��: ���ص�ǰ�б��ж��ٴ��ڵ�ǰֵ
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
	 *@auther ������
	 *
	 *@ע��:��ȡ��ǰ�����г��ȵ�λ���Ա�����ȷ����ǰ���ϳ��ȵ��������
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
	 *@auther ������
	 *
	 *@ע��:�滻����([],)
*/
	public static String th(String str) {
		return str.replace("[", "").toString().replace("]", "").replace(",","+");
	}
	/**
	 *@ע��:�����ʺϵĳ���
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
	public static double zuishihelist(List<Double> list, double value) {// ��ȡ��ǰ�б��вü������ٵ���
		/*System.out.println(shengyu + "--"
				+ Arrays.toString(list.toArray()));*/
		double start = list.get(0);
		if (list.size() != 0) {//�жϵ�ǰ�б�Ϊ��
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
	
	public static int getIndex(int[] demandData, int value) {//��ȡ�±�
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
	 *@ע��:���ص�ǰ�Ŀ��(�ѿ�洦����Գ���Ϊkey��Map����   ����ɵ�ǰ��ǰ���͵ĵ�ǰ���)
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
