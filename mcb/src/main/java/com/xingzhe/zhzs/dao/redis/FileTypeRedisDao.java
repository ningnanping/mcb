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
public class FileTypeRedisDao extends BaseRedisDao
{
    
    private static final Logger logger = LoggerFactory.getLogger(FileTypeRedisDao.class);
    
    @Autowired
    private RedisCache redisCache;
    
    public boolean saveFileTypeByDeptId(final List<SelectBoxObj> list, final String deptId)
    {
        try
        {
            return  redisCache.putMap(REDIS_KEY_PREFIX_FILETYPE_DEPTID, deptId, JSON.toJSONString(list));
        }
        catch (Exception e)
        {
            logger.error(e.toString());
            return false;
        }
    }
    
    public List<SelectBoxObj> getFileTypeByDeptId(final String deptId)
    {
        try
        {
            return JSON.parseArray((String) redisCache.getMap(REDIS_KEY_PREFIX_FILETYPE_DEPTID, deptId), SelectBoxObj.class);
        }
        catch (Exception e)
        {
            logger.error(e.toString());
            return null;
        }
    }
}
