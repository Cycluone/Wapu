package com.yb.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import redis.clients.jedis.Jedis;

import com.yb.bean.ArrayData;
import com.yb.bean.ArrayData1;
import com.yb.bean.D;
import com.yb.bean.IntAll;
import com.yb.bean.Material;
import com.yb.bean.Results;
import com.yb.control.ExecutorsJdbc;

public class SF {
	public static Jedis redis = new Jedis("127.0.0.1", 6379);
	private static List<IntAll> lengthList = null;
	private static Map<String, List<IntAll>> map = new HashMap<String, List<IntAll>>();

	// 利用率
	public static void main(String[] args) {
		//getMaterial();
		getCapture();
	}
	public static void writeDemandRedis(String ExcelServerRCID) {// 把需要优化的数据写入Redis里面
		Map<String, String> modelAll = ExecutorsJdbc
				.getModelAll(ExcelServerRCID);
		Set<Entry<String, String>> set = modelAll.entrySet();
		for (Entry<String, String> entry : set) {
			String model = entry.getKey();
			List<IntAll> lengthAll = ExecutorsJdbc.getLengthAll(
					ExcelServerRCID, model);
			lengthList = new ArrayList<IntAll>();
			for (int i = 0; i < lengthAll.size(); i++) {
				IntAll all = lengthAll.get(i);
				lengthList.add(all);
			}
			map.put(model, lengthList);
		}
	}
	public static List<ListArrayData> getCapture() {
		String ExcelServerRCID = "rc20171025000073";
		writeDemandRedis(ExcelServerRCID);
		Map<String, String> modelAll = ExecutorsJdbc
				.getModelAll(ExcelServerRCID);
		Set<Entry<String, String>> set = modelAll.entrySet();
		List<ListArrayData> arrdata=new ArrayList<ListArrayData>();
		
		List<List<Double>> all=new ArrayList<List<Double>>();
		for (Entry<String, String> entry : set) {
			String model = entry.getKey();
			String color = entry.getValue();
			List<IntAll> intAll = map.get(model);// 当前型号 数据
			List<Double> list=new ArrayList<Double>();
			for (int i = 0; i < intAll.size(); i++) {
				IntAll intAll2 = intAll.get(i);
				for (int j = 0; j <intAll2.getQuantity(); j++) {
					list.add(Double.parseDouble(intAll2.getLength()+""));
				}
			}
			List<Material> material = ExecutorsJdbc.getMaterial(model, color);// 正余料库存
			double[] c = new double[material.size()];
			int[] d = new int[material.size()];
			String[] type = new String[material.size()];
			for (int i = 0; i < material.size(); i++) {
				Material m = material.get(i);
				c[i] = m.getLength();
				d[i] = m.getQuantity();
				type[i] = m.getType();
			}
			ArrayData1 ad=new ArrayData1(c,d,type);
			ListArrayData arrda=new ListArrayData(model,list,ad);
			arrdata.add(arrda);
			//System.out.println(model + "当前型号需求数据" + Arrays.toString(list.toArray()));
		}
		return arrdata;
	}

}
