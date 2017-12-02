package com.x.mainTest.algorithm;

public class Algorithm {
	
	public static void Hanoi(int n,char a,char b,char c){//河内之塔
		if(n==1){
			System.out.println("Move sheet "+n+" from "+a+" to "+c);
		}else{
			Hanoi(n-1,'A','B','C');
			System.out.println("Move sheet "+n+" from "+a+" to "+c);
			Hanoi(n-1,'B','A','C');
		}
	}
	public static void main(String[] args) {
		long s = System.currentTimeMillis();
		Hanoi(16,'A','B','C');
		long t = System.currentTimeMillis();
		//demos();
		System.out.println("需要时间"+(t-s));
		
	}
}
