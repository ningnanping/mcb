package com.xingzhe.framework.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

@Configuration
public class RedisBeanConfig
{
    private Logger logger = LoggerFactory.getLogger(RedisBeanConfig.class);
    
    public static boolean REDIS_USE = false;
    public static boolean REDIS_INIT_USE = false;
    
    @Value("#{redis['redis1.ip']}")
    private String redis1Ip;
    
    @Value("#{redis['redis1.port']}")
    private int redis1Port;
    
    @Value("#{redis['redis.pool.maxActive']}")
    private int redisPoolMaxActive;
    
    @Value("#{redis['redis.pool.maxIdle']}")
    private int redisPoolMaxIdle;
    
    @Value("#{redis['redis.pool.maxWait']}")
    private int redisPoolMaxWait;
    
    @Value("#{redis['redis.pool.testOnBorrow']}")
    private boolean redisPoolTestOnBorrow;
    
    @Value("#{redis['redis.pool.testOnReturn']}")
    private boolean redisPoolTestOnReturn;
    
    @Value("#{redis['redis.use']}")
    private boolean redisUse;
    
    @Value("#{redis['redis.init.use']}")
    private boolean redisInitUse;
    
    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig getJedisPoolConfig()
    {
        logger.debug("初始化  JedisPoolConfig ");
        logger.debug(redis1Ip);
        logger.debug("" + redisInitUse);
        JedisPoolConfig j = new JedisPoolConfig();
        j.setMaxActive(redisPoolMaxActive);
        j.setMaxIdle(redisPoolMaxIdle);
        j.setMaxWait(redisPoolMaxWait);
        j.setTestOnBorrow(redisPoolTestOnBorrow);
        j.setTestOnReturn(redisPoolTestOnReturn);
        RedisBeanConfig.REDIS_INIT_USE = redisUse;
        if (RedisBeanConfig.REDIS_INIT_USE)
        {
            RedisBeanConfig.REDIS_USE = redisInitUse;
        }
        return j;
    }
    
    @Bean(name = "shardedJedisPool")
    public ShardedJedisPool getShardedJedisPool()
    {
        List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
        list.add(getJedisShardInfo());
        ShardedJedisPool sj = new ShardedJedisPool(getJedisPoolConfig(), list);
        return sj;
    }
    
    @Bean(name = "jedisShardInfo1")
    public JedisShardInfo getJedisShardInfo()
    {
        return new JedisShardInfo(redis1Ip, redis1Port);
    }
}
