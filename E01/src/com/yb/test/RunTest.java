package com.yb.test;

import com.yb.control.ExecutorsJdbc;

public class RunTest {
	public static void main(String[] args) {
		//System.out.println(System.getProperty("user.dir")+"\\WebRoot\\file");
		//String exe = ExecutorsJdbc.exe("rc20171107000041","S171030002-1","D:\\Der\\Dowloads\\apache-tomcat-6.0.35\\webapps\\EsApp\\file\\S171030002-1.xls");
		//System.out.println(exe);
		//int startwith=15/(3*1.0);
		// int count=ExecutorsJdbc.getCountData();//���ݿ���������
		 //int row=5;//��ʾ����
		// int pages=(int) Math.ceil(count/(row*1.0));//��ҳ��
		 //int page=3;// ��ǰҳ
		// int startwith=page>1?row:1;
		// int endwith=row+row<ExecutorsJdbc.getCountData()?row+row:ExecutorsJdbc.getCountData();
		 
		 /**
		  * 1 1-10
		  * 2 11-20
		  * 3 21 -30
		  * */
		 
		/* int[] pages={1,2,3,4};
		 int count=16;//������
		 int row=10;//��ʾ����
		 int limit = 10; // ÿҳ��ʾ��¼����
		 int totalpages =(int)Math.ceil(count/(limit*1.0)) ; // ��ҳ��
		 for (int i = 1; i <=totalpages; i++) {
			 	int page=pages[i];
				page=(page*row)>count?page-1:page;//�����ʾ�����������ݿ�����  �ͼ���һҳ
				int startwith=page!=1?(page*row):1;//���㿪ʼ������
				startwith=startwith>count?startwith-row:startwith;//�����ʼ���ݴ������ݿ����ݾͼ���10�� ������ü��������
				int endwith=page!=1?startwith+row:startwith+row-1;//������ǵ�һҳ���ݾͼ�һҳ����  �ǵĻ��ʹӵ�һ�����ݿ�ʼ
				endwith=endwith>count?count:endwith;//���������ݴ������ݿ��  ��ʹ����������������ݿ�����
				System.out.println(startwith+"--"+endwith+"--"+count+"--"+page+"--"+row);
		 }*/
		 
		 /*if(row*2>ExecutorsJdbc.getCountData()){
			 startwith=row*page;
		 }else{
			 startwith=row*(page-1);
		 }*/
		/* int h=1;
		 int c=2;*/
		 //System.out.println(1<2);
		
	}
}
