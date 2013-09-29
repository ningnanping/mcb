package com.xingzhe.zhzs.dao.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.xingzhe.framework.dao.redis.BaseRedisDao;
import com.xingzhe.framework.domain.SelectBoxObj;
import com.xinzhe.framework.cache.redis.RedisCache;

@Repository
public class RevenueTypeRedisDao extends BaseRedisDao
{
    private static final Logger logger = LoggerFactory.getLogger(RevenueTypeRedisDao.class);
    
    @Autowired
    private RedisCache redisCache;
    
    public boolean saveTaxType(final List<SelectBoxObj> list)
    {
        try
        {
            return redisCache.putMap("COMMON", REDIS_KEY_PREFIX_TAXTYPE, JSON.toJSONString(list));
        }
        catch (Exception e)
        {
            logger.error(e.toString());
            return false;
        }
    }
    
    public List<SelectBoxObj> queryTaxTypeList()
    {
        try
        {
            String s = redisCache.getMap("COMMON", REDIS_KEY_PREFIX_TAXTYPE);
            return JSON.parseArray(s, SelectBoxObj.class);
        }
        catch (Exception e)
        {
            logger.error("Read DepartmentInfo into redis cause error , maybe redis is down !");
            return null;
        }
    }
    
    
}
