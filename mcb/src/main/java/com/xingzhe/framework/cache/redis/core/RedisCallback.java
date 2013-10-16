package com.xingzhe.framework.cache.redis.core;

import redis.clients.jedis.ShardedJedis;

/**
 * 类RedisCallback.java的实现描述：Redis的回调接口
 */
public interface RedisCallback<T> {

    /**
     * Redis 命令回调方法。
     * 
     * @param jedis
     * @param key
     * @return
     */
    T doInRedis(ShardedJedis jedis);

}
