package com.yb.test;

public class SteelCutting {  
	  
    public static void main(String[] args) {  
        SteelCutting s = new SteelCutting();  
        int[] p = new int[] { 0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 25};  
        System.out.println(s.extendedBottomUpCutRod(p, 4));  
    }  
  
    /** 
     * 递归实现钢铁切割 
     * @param 下标p 
     * @param 下标n 
     * @return 
     */  
    int cutRod(int[] p, int n) {  
        if (n == 0) {  
            return 0;  
        }  
        int q = Integer.MIN_VALUE;  
        for (int i = 1; i <= n; i++) {  
            q = max(q, p[i] + cutRod(p, n - i));  
            System.out.println("执行i="+i+" n="+n+" q="+q);  
            if (i==n){  
                System.out.println("子问题规模为 "+n+" 的最优值 = "+q);  
            }  
        }  
        return q;  
    }  
      
    /** 
     * 备忘切割 
     * @param 数组p 
     * @param 下标n 
     * @return 
     */  
    int memoizedCutRod(int[] p, int n){  
        int[] r = new int[n + 1];  
        for (int i = 0; i <= n; i++) {  
            r[i] = Integer.MIN_VALUE;  
        }  
        return memoizedCutRodAux(p, n, r);  
    }  
      
    /** 
     * 备忘切割 
     * @param 数组p 
     * @param 下标n 
     * @param 下标r 
     * @return 
     */  
    int memoizedCutRodAux(int[] p, int n, int r[]){  
        if(r[n] >= 0){  
            return r[n];  
        }  
        int q = Integer.MIN_VALUE;  
        if(n == 0){  
            q = 0;  
        }else {  
            for (int i = 1; i <= n; i++) {  
                q = max(q, p[i] + memoizedCutRodAux(p, n - i, r));  
                System.out.println("执行i="+i+" n="+n+" q="+q);  
                if (i==n){  
                    System.out.println("子问题规模为 "+n+" 的最优值 = "+q);  
                }  
            }  
        }  
        //将每一次求的长度为 n 的最优值保存在数组 r 里面  
        r[n] = q;  
        return q;  
    }  
      
    /** 
     * 自顶向下切割 
     * @param 数组p 
     * @param 下标n 
     * @return 
     */  
    int bottomUpCutRod(int[] p, int n){  
        int[] r = new int[n + 1];  
        r[0] = 0;  
        int q = Integer.MIN_VALUE;  
        for (int i = 1; i <= n; i++) {  
            for (int j = 1; j <= i; j++) {  
                q = max(q, p[j] + r[i - j]);  
                System.out.println("执行i="+i+" j="+j+" q="+q);  
            }  
            r[i] = q;  
            System.out.println("子问题规模为 "+i+" 的最优值 = "+q);  
        }  
        return r[n];  
    }  
      
    /** 
     * 自顶向下切割（扩展） 
     * @param 数组p 
     * @param 下标n 
     * @return 
     */  
    int extendedBottomUpCutRod(int[] p, int n){  
        int[] r = new int[n + 1];  
        int[] s = new int[n + 1];  
        r[0] = 0;  
        int q = Integer.MIN_VALUE;  
        for (int i = 1; i <= n; i++) {  
            for (int j = 1; j <= i; j++) {  
                if(q < p[j] + r[i - j]){  
                    q = p[j] + r[i - j];  
                    s[i] = j;  
                }  
                System.out.println("执行i="+i+" j="+j+" q="+q);  
            }  
            r[i] = q;  
            System.out.println("子问题规模为 "+i+" 的最优值 = "+q);  
        }  
        System.out.println(s[n]);  
        return r[n];  
    }  
      
  
    public int max(int a, int b) {  
        return a > b ? a : b;  
    }  
  
}  