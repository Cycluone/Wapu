package com.x.mainTest.learning.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import redis.clients.jedis.Jedis;

public class LearnDemo01 {
	private static Jedis jedis = new Jedis("127.0.0.1");

	/**
	 * ����֮ǰȷ��Redis�����Ѿ�����
	 * 
	 * ѧϰ֮Redis֮��������
	 * */

	/**
	 * string��redis����������ͣ������������Memcachedһģһ�������ͣ�һ��key��Ӧһ��value��
	 * string�����Ƕ����ư�ȫ�ġ���˼��redis��string���԰����κ����ݡ�����jpgͼƬ�������л��Ķ��� ��
	 * string������Redis��������������ͣ�һ��������ܴ洢512MB��
	 */
	public void demoString() {// String
		jedis.set("name", "dengshuxing");
		String name = jedis.get("name");
		System.out.println(name);// ���:dengshuxing
	}

	/**
	 * Redis hash ��һ����ֵ(key=>value)�Լ��ϡ� Redis
	 * hash��һ��string���͵�field��value��ӳ���hash�ر��ʺ����ڴ洢����
	 */
	public void demoHash() {// Hash(��ֵ��)
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < 2; i++) {
			map.put(i + "��", "���ǵ�" + i + "��");
		}
		jedis.hmset("ren", map);
		Map<String, String> all = jedis.hgetAll("ren");
		Set<Entry<String, String>> set = all.entrySet();
		for (Entry<String, String> e : set) {
			System.out.println(e.getKey() + "---" + e.getValue());
			/**
			 * ���: 1��---���ǵ�1�� 0��---���ǵ�0��
			 * */
		}
	}

	/** Redis �б��Ǽ򵥵��ַ����б����ղ���˳��������������һ��Ԫ�ص��б��ͷ������ߣ�����β�����ұߣ��� */
	public void demoList() {// List(�б�)
		for (int i = 0; i < 2; i++) {
			jedis.lpush("list", "list" + i);
		}
		List<String> list = jedis.lrange("list", 0, 2);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			/**
			 * ���:list1 list0
			 * */
		}
	}

	/*
	 * Redis��Set��string���͵����򼯺ϡ� ������ͨ����ϣ��ʵ�ֵģ�������ӣ�ɾ�������ҵĸ��Ӷȶ���O(1)��
	 */
	public void demoSet() {// Set(����)
		for (int i = 0; i < 2; i++) {
			jedis.sadd("set", "set" + i);
			jedis.sadd("set", "set0");
		}
		Set<String> set = jedis.smembers("set");
		for (String s : set) {
			System.out.println(s);
			/**
			 * ���:set1 set0
			 * */
		}
	}

	/**
	 * Redis zset �� set һ��Ҳ��string����Ԫ�صļ���,�Ҳ������ظ��ĳ�Ա��
	 * ��ͬ����ÿ��Ԫ�ض������һ��double���͵ķ�����redis����ͨ��������Ϊ�����еĳ�Ա���д�С���������
	 * zset�ĳ�Ա��Ψһ��,������(score)ȴ�����ظ���
	 * */
	public void demoZset() {// Set(����ļ���)
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				jedis.zadd("zset", i, (i * j) + "");
			}
		}
		Set<String> set = jedis.zrangeByScore("zset", 0, Double.MAX_VALUE);
		for (String zset : set) {
			System.out.println(zset);
			/**
			 *��� 0 1
			 */
		}

	}

}
