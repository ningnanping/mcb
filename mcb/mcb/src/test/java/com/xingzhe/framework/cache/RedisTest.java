package com.xingzhe.framework.cache;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cctc.framework.test.BaseTestCase;
import com.xinzhe.framework.cache.redis.RedisCache;

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
