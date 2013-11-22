package com.xingzhe.framework.cache.redis;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.xingzhe.framework.config.RedisBeanConfig;

@Aspect
@Component
public class RedisCacheAspect implements Ordered
{
    
    private static Logger log = LoggerFactory.getLogger(RedisCacheAspect.class);
    
    @Autowired
    private RedisCache redisCacheManager;
    private int orderValue = 3;
    
    public RedisCache getRedisCacheManager()
    {
        return redisCacheManager;
    }
    
    public void setRedisCacheManager(RedisCache redisCacheManager)
    {
        this.redisCacheManager = redisCacheManager;
    }
    
    @Pointcut("@annotation(com.xingzhe.framework.cache.redis.NeedRedisCached)")
    public void needRedisCached()
    {
        
    }
    
    @Around("needRedisCached()")
    public Object aroundInvoke(ProceedingJoinPoint pjp) throws Throwable
    {
        boolean cacheEnabled = RedisBeanConfig.REDIS_USE;
        
        if (cacheEnabled)
        {
            log.info("enter aroundInvoke!!!");
            String targetName = pjp.getTarget().getClass().getName();
            String methodName = pjp.getSignature().getName();
            Object[] arguments = pjp.getArgs();
            
            Class<?> targetClass = Class.forName(targetName);
            Method[] method = targetClass.getMethods();
            String type = null;
            Class<?> returnType = null;
            String endKey = null;
            String preKey = null;
            int[] keyArgs = {};
            boolean isArray = false;
            int time = -1;
            for (Method m : method)
            {
                if (m.getName().equals(methodName))
                {
                    Class<?>[] tmpCs = m.getParameterTypes();
                    if (tmpCs.length == arguments.length)
                    {
                        NeedRedisCached needRedisCached = m.getAnnotation(NeedRedisCached.class);
                        type = needRedisCached.type();
                        returnType = needRedisCached.returnType();
                        endKey = needRedisCached.endKey();
                        preKey = needRedisCached.preKey();
                        keyArgs = needRedisCached.keyArgs();
                        isArray = needRedisCached.isArray();
                        time = needRedisCached.time();
                        break;
                    }
                }
            }
            
            Object value = null;
            if ("HASH".equalsIgnoreCase(type))
            {
                if ("COMMON".equalsIgnoreCase(preKey))
                {
                    if (StringUtils.isNotBlank(endKey))
                    {
                        value = redisCacheManager.getMap("COMMON", endKey);
                    }
                    else
                    {
                        log.error("当preKey为COMMOM时,endKey不得为空");
                    }
                }
                else
                {
                    StringBuilder keys = new StringBuilder(50);
                    for (int i : keyArgs)
                    {
                        keys.append(arguments[i - 1].toString()).append("_");
                    }
                    try
                    {
                        value = redisCacheManager.getMap(preKey, keys.toString());
                    }
                    catch (Exception e)
                    {
                        RedisBeanConfig.REDIS_USE = false;
                        log.info("redis is  down !please check...");
                    }
                }
            }
            else if ("STRING".equalsIgnoreCase(type))
            {
                StringBuilder key = new StringBuilder(50);
                key.append(preKey).append("_").append(endKey).append("_");
                for (int i : keyArgs)
                {
                    key.append(arguments[i - 1].toString()).append("_");
                }
                redisCacheManager.get(key.toString());
                
            }
            else if ("LIST".equalsIgnoreCase(type))
            {
                // TODO
            }
            else if ("SET".equalsIgnoreCase(type))
            {
                // TODO
            }
            else if ("SORTED_SET".equalsIgnoreCase(type))
            {
                // TODO
            }
            
            if (null != value)
            {
                if (isArray)
                {
                    return JSON.parseArray((String) value, returnType);
                }
                else
                {
                    return JSON.parseObject((String) value, returnType);
                }
                // return value;
            }
            else if ("null".equals(value))
            {
                return null;
            }
            else
            {
                value = pjp.proceed();
                if (null != value && StringUtils.isNotBlank(value.toString()))
                {
                    if ("HASH".equalsIgnoreCase(type))
                    {
                        if ("COMMON".equalsIgnoreCase(preKey))
                        {
                            if (StringUtils.isNotBlank(endKey))
                            {
                                redisCacheManager.putMap("COMMON", endKey, JSON.toJSONString(value));
                            }
                            else
                            {
                                log.error("当preKey为COMMOM时,endKey不得为空");
                            }
                        }
                        else
                        {
                            StringBuilder keys = new StringBuilder(50);
                            for (int i : keyArgs)
                            {
                                keys.append(arguments[i - 1].toString()).append("_");
                            }
                            redisCacheManager.putMap(preKey, keys.toString(), JSON.toJSONString(value));
                        }
                    }
                    else if ("STRING".equalsIgnoreCase(type))
                    {
                        StringBuilder key = new StringBuilder(50);
                        key.append(preKey).append("_").append(endKey).append("_");
                        for (int i : keyArgs)
                        {
                            key.append(arguments[i - 1].toString()).append("_");
                        }
                        if (time != -1)
                        {
                            redisCacheManager.put(key.toString(), JSON.toJSONString(value));
                        }
                        else
                        {
                            redisCacheManager.put(key.toString(), JSON.toJSONString(value), time);
                        }
                    }
                    else if ("LIST".equalsIgnoreCase(type))
                    {
                        // TODO
                    }
                    else if ("SET".equalsIgnoreCase(type))
                    {
                        // TODO
                    }
                    else if ("SORTED_SET".equalsIgnoreCase(type))
                    {
                        // TODO
                    }
                }
            }
            return value;
        }
        else
        {
            // 执行hbase查询
            return pjp.proceed();
        }
    }
    
    @Override
    public int getOrder()
    {
        return orderValue;
    }
    
    public int getOrderValue()
    {
        return orderValue;
    }
    
    public void setOrderValue(int orderValue)
    {
        this.orderValue = orderValue;
    }
}
