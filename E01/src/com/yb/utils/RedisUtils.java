package com.yb.utils;

import redis.clients.jedis.Jedis;

public class RedisUtils extends Jedis{
	public static Jedis redis=new Jedis("127.0.0.1",6379);
 
	 
}
