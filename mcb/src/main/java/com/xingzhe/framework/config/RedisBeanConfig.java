package com.xingzhe.framework.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import com.xingzhe.framework.util.PropertiesReaderUtil;

@Configuration
public class RedisBeanConfig {
	private Logger logger = LoggerFactory.getLogger(RedisBeanConfig.class);
	
	public static boolean REDIS_USE=false;
    public static boolean REDIS_INIT_USE =false;

	public RedisBeanConfig() {
		logger.debug("初始化......RedisBeanConfig........");
		PropertiesReaderUtil.init("redis.properties");
	}

	@Bean(name = "jedisPoolConfig")
	public JedisPoolConfig getJedisPoolConfig() {
		logger.debug("初始化  JedisPoolConfig ");
		Properties pro = PropertiesReaderUtil.getPro();
		JedisPoolConfig j = new JedisPoolConfig();
		j.setMaxActive(Integer.parseInt(pro.getProperty("redis.pool.maxActive")));
		j.setMaxIdle(Integer.parseInt(pro.getProperty("redis.pool.maxIdle")));
		j.setMaxWait(Integer.parseInt(pro.getProperty("redis.pool.maxWait")));
		j.setTestOnBorrow(Boolean.parseBoolean(pro.getProperty("redis.pool.testOnBorrow")));
		RedisBeanConfig.REDIS_INIT_USE=Boolean.parseBoolean(pro.getProperty("redis.init.use")==null?"FALSE":pro.getProperty("redis.init.use"));
		if(RedisBeanConfig.REDIS_INIT_USE){
		    RedisBeanConfig.REDIS_USE=Boolean.parseBoolean(pro.getProperty("redis.use")==null?"FALSE":pro.getProperty("redis.use"));
		}
		return j;
	}

	@Bean(name = "shardedJedisPool")
	public ShardedJedisPool getShardedJedisPool() {
		List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
		list.add(getJedisShardInfo());
		ShardedJedisPool sj = new ShardedJedisPool(getJedisPoolConfig(), list);
		return sj;
	}

	@Bean(name = "jedisShardInfo1")
	public JedisShardInfo getJedisShardInfo() {
		Properties pro = PropertiesReaderUtil.getPro();
		String ip = pro.getProperty("redis1.ip");
		String port = pro.getProperty("redis1.port");
		JedisShardInfo ji = new JedisShardInfo(ip, Integer.parseInt(port));
		return ji;
	}
}
