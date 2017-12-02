package com.yb.test;

import com.yb.control.ExecutorsJdbc;

public class RunTest {
	public static void main(String[] args) {
		//System.out.println(System.getProperty("user.dir")+"\\WebRoot\\file");
		//String exe = ExecutorsJdbc.exe("rc20171107000041","S171030002-1","D:\\Der\\Dowloads\\apache-tomcat-6.0.35\\webapps\\EsApp\\file\\S171030002-1.xls");
		//System.out.println(exe);
		//int startwith=15/(3*1.0);
		// int count=ExecutorsJdbc.getCountData();//数据库数据数量
		 //int row=5;//显示数量
		// int pages=(int) Math.ceil(count/(row*1.0));//总页数
		 //int page=3;// 当前页
		// int startwith=page>1?row:1;
		// int endwith=row+row<ExecutorsJdbc.getCountData()?row+row:ExecutorsJdbc.getCountData();
		 
		 /**
		  * 1 1-10
		  * 2 11-20
		  * 3 21 -30
		  * */
		 
		/* int[] pages={1,2,3,4};
		 int count=16;//总数量
		 int row=10;//显示数量
		 int limit = 10; // 每页显示记录条数
		 int totalpages =(int)Math.ceil(count/(limit*1.0)) ; // 总页数
		 for (int i = 1; i <=totalpages; i++) {
			 	int page=pages[i];
				page=(page*row)>count?page-1:page;//如果显示数量大于数据库数量  就减少一页
				int startwith=page!=1?(page*row):1;//计算开始的数据
				startwith=startwith>count?startwith-row:startwith;//如果开始数据大于数据库数据就减少10条 否则就用计算出来的
				int endwith=page!=1?startwith+row:startwith+row-1;//如果不是第一页数据就加一页数据  是的话就从第一条数据开始
				endwith=endwith>count?count:endwith;//如果最大数据大于数据库的  就使用最大数据量的数据库数量
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
