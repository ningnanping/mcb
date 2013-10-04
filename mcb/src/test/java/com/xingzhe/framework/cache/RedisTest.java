package com.xingzhe.framework.cache;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xingzhe.framework.cache.redis.RedisCache;
import com.xingzhe.framework.test.BaseTestCase;

public class RedisTest extends BaseTestCase{
	
	private static Logger logger = Logger.getLogger(RedisTest.class);
	
	@Autowired
	RedisCache redisCache;
	
	@Test
	public void  test(){
		redisCache.put("111", "222");
		
		logger.debug("done!!!!!!!!!!!!!!!!!!!!!!");
	}
	
}
