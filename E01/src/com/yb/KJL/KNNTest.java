package com.yb.KJL;
import java.util.ArrayList;
import java.util.List;

public class KNNTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<KNNData> kd = new ArrayList<KNNData>();
        //ÑµÁ·¼¯
        kd.add(new KNNData(1.2,1.1,0.1,"A"));
        kd.add(new KNNData(1.2,1.1,0.1,"A"));
        kd.add(new KNNData(7,1.5,0.1,"B"));
        kd.add(new KNNData(6,1.2,0.1,"B"));
        kd.add(new KNNData(2,2.6,0.1,"C"));
        kd.add(new KNNData(2,2.6,0.1,"C"));
        kd.add(new KNNData(2,2.6,0.1,"C"));
        kd.add(new KNNData(100,1.1,0.1,"D"));

        System.out.println(KNN.knnCal(3, new KNNData(1.1,1.1,0.1,"N/A"), kd));
	}

}
