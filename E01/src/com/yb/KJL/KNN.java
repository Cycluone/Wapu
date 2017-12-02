package com.yb.KJL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class KNN {
	//ѵ����
    private List<KNNData> KNNDS = null;
    
    public KNN(List<KNNData> KNNDS) {
        this.KNNDS = KNNDS;
    }
    
    //ŷʽ����
    private static double disCal(KNNData i, KNNData td) {
        return Math.sqrt((i.c1 - td.c1)*(i.c1 - td.c1)+(i.c2 - td.c2)*(i.c2 - td.c2)+
                (i.c3 - td.c3)*(i.c3 - td.c3));
    }
    
    private static String getMaxValueKey(int k, List<KNNData> ts){
        //ֻ����ǰk��Ԫ��
        
        while(ts.size() != k) {
            ts.remove(k);
        }
                
        String sKey;
        //����key�Լ����ִ���
        HashMap<String,Integer> keySet = new HashMap<String,Integer>();
        keySet.put(ts.get(0).type,1);
        for (int x = 1; x < ts.size(); x++) {
            sKey = ts.get(x).type;
            if (keySet.containsKey(sKey)) {
                keySet.put(sKey, keySet.get(sKey)+1);
            } else {
                keySet.put(sKey, 1);
            }
        }
        Set<Map.Entry<String,Integer>> set = keySet.entrySet();
        Iterator<Map.Entry<String,Integer>> iter = set.iterator(); 
        
        int mValue = 0;
        String mType = "";
        while (iter.hasNext()){
            Map.Entry<String,Integer> map = iter.next();
            if (mValue < map.getValue()) {
                mType = map.getKey();
                mValue = map.getValue();
            }
        }
        
        return mType;
    }
    
    public static String knnCal(int k, KNNData i, List<KNNData> ts) {
        //�������
        for (KNNData td : ts) {
        	System.out.println(td.distance+"---"+disCal(i, td));
            td.distance = disCal(i, td);
        }
        Collections.sort(ts);    
        return getMaxValueKey(k, ts);
    }
}
