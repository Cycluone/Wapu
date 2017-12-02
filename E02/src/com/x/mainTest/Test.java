package com.x.mainTest;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import redis.clients.jedis.Jedis;

public class Test {
	private static Jedis jedis = new Jedis("127.0.0.1");

	public static void main(String[] args) {
		redis();
	}

	/**
	 * 对Redis 进行学习
	 * */
	public static void redis() {

		try {
			String ping = jedis.ping();
			System.out.println(ping);

		} catch (Exception e) {
			System.out.println("请开启或检查您的Redis服务是否开启正确！" + e.getMessage());
		}
/*
		Boolean exists = jedis.exists("users");
		System.out.println(exists);
		Set<String> set = jedis.keys("*");
		for (String s : set) {
			System.out.println(s);
		}
		Map<String, String> all = jedis.hgetAll("users:1");
		Set<Entry<String,String>> entrySet = all.entrySet();
		for (Entry<String, String> e : entrySet) {
			System.out.println(e.getKey()+"----"+e.getValue());
		}
	 
		Long del = jedis.del("name");
		System.out.println(del);*/
		
		Long scard = jedis.scard("set");
		System.out.println(scard);
		jedis.multi(); 
	}
}
