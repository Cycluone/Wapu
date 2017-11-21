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
	 * 运用之前确保Redis服务已经开启
	 * 
	 * 学习之Redis之数据类型
	 * */

	/**
	 * string是redis最基本的类型，你可以理解成与Memcached一模一样的类型，一个key对应一个value。
	 * string类型是二进制安全的。意思是redis的string可以包含任何数据。比如jpg图片或者序列化的对象 。
	 * string类型是Redis最基本的数据类型，一个键最大能存储512MB。
	 */
	public void demoString() {// String
		jedis.set("name", "dengshuxing");
		String name = jedis.get("name");
		System.out.println(name);// 结果:dengshuxing
	}

	/**
	 * Redis hash 是一个键值(key=>value)对集合。 Redis
	 * hash是一个string类型的field和value的映射表，hash特别适合用于存储对象。
	 */
	public void demoHash() {// Hash(键值对)
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < 2; i++) {
			map.put(i + "号", "这是第" + i + "号");
		}
		jedis.hmset("ren", map);
		Map<String, String> all = jedis.hgetAll("ren");
		Set<Entry<String, String>> set = all.entrySet();
		for (Entry<String, String> e : set) {
			System.out.println(e.getKey() + "---" + e.getValue());
			/**
			 * 结果: 1号---这是第1号 0号---这是第0号
			 * */
		}
	}

	/** Redis 列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）。 */
	public void demoList() {// List(列表)
		for (int i = 0; i < 2; i++) {
			jedis.lpush("list", "list" + i);
		}
		List<String> list = jedis.lrange("list", 0, 2);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			/**
			 * 结果:list1 list0
			 * */
		}
	}

	/*
	 * Redis的Set是string类型的无序集合。 集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。
	 */
	public void demoSet() {// Set(集合)
		for (int i = 0; i < 2; i++) {
			jedis.sadd("set", "set" + i);
			jedis.sadd("set", "set0");
		}
		Set<String> set = jedis.smembers("set");
		for (String s : set) {
			System.out.println(s);
			/**
			 * 结果:set1 set0
			 * */
		}
	}

	/**
	 * Redis zset 和 set 一样也是string类型元素的集合,且不允许重复的成员。
	 * 不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。
	 * zset的成员是唯一的,但分数(score)却可以重复。
	 * */
	public void demoZset() {// Set(有序的集合)
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				jedis.zadd("zset", i, (i * j) + "");
			}
		}
		Set<String> set = jedis.zrangeByScore("zset", 0, Double.MAX_VALUE);
		for (String zset : set) {
			System.out.println(zset);
			/**
			 *结果 0 1
			 */
		}

	}

}
