package com.x.mainTest.learning;

import redis.clients.jedis.JedisPoolConfig;

import com.x.mainTest.learning.redis.LearnDemo01;

public class DemoTest {
	private static LearnDemo01 redisLearn=new LearnDemo01();
	public static void main(String[] args) {
		  JedisPoolConfig config = new JedisPoolConfig();
		redisLearn.demoString();
		redisLearn.demoHash();
		redisLearn.demoList();
		redisLearn.demoSet();
		redisLearn.demoZset();
	}

}
