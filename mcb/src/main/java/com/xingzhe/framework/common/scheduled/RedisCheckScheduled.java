package com.xingzhe.framework.common.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xingzhe.framework.cache.redis.RedisCache;
import com.xingzhe.framework.config.RedisBeanConfig;

@Component
public class RedisCheckScheduled
{
    private static final Logger loger = LoggerFactory.getLogger(RedisCheckScheduled.class);
    
    @Autowired
    private RedisCache redisCache;
    
    @Scheduled(fixedRate = 1200000)
    public void processCheckRedis()
    {
        if (RedisBeanConfig.REDIS_INIT_USE && !RedisBeanConfig.REDIS_USE)
            
            try
            {
                loger.debug("###############check  redis###############");
                redisCache.get("CHECK");
                loger.debug("redisCache is ok ");
                RedisBeanConfig.REDIS_USE = true;
            }
            catch (Exception e)
            {
                loger.debug("redisCache is  down");
                RedisBeanConfig.REDIS_USE = false;
            }
    }
}
