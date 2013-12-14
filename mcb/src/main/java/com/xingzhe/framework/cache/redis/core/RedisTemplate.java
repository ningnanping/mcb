package com.xingzhe.framework.cache.redis.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 类RedisTemplate.java的实现描述：Redis 模板类
 * 
 */
@Repository
public class RedisTemplate<Key, Val>
{
    
    private static final Logger logger=LoggerFactory.getLogger(RedisTemplate.class);
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    
    /**
     * redis 命令执行核心方法.
     * 
     * @param redisCallback
     * @return
     */
    public <V> V execute(RedisCallback<V> redisCallback)
    {
        
        ShardedJedis jedis = null;
        try
        {
            jedis = shardedJedisPool.getResource();
        }
        catch (JedisConnectionException e)
        {
            logger.error("无法获取连接：{}",e.getMessage());
        }
        try
        {
            return redisCallback.doInRedis(jedis);
        }
        finally
        {
            shardedJedisPool.returnBrokenResource(jedis);
        }
        
    }
    
    /**
     * shardedJedisPool destroy
     */
    public void destroy()
    {
        shardedJedisPool.destroy();
    }
    
}
