package com.yb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static String getSqlValue(String value) {
		return "\"" + value + "\"";
	}

	public static void main(String[] args) {
		System.out.println(getNumbers("8026"));
	}

	public static void test() {

	}

	// 判断一个字符串是否都为数字
	public static boolean isDigit(String strNum) {
		return strNum.matches("[0-9]{1,}");
	}

	// 截取数字
	public static String getNumbers(String content) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}
	// 截取非数字  
	public String splitNotNumber(String content) {  
	    Pattern pattern = Pattern.compile("\\D+");  
	    Matcher matcher = pattern.matcher(content);  
	    while (matcher.find()) {  
	        return matcher.group(0);  
	    }  
	    return "";  
	} 
}
