package com.xingzhe.zhzs.dao.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.xingzhe.framework.dao.redis.BaseRedisDao;
import com.xingzhe.framework.domain.SelectBoxObj;

@Repository
public class DepartmentRedisDao extends BaseRedisDao
{
    
    private static final Logger logger = LoggerFactory.getLogger(DepartmentRedisDao.class);
    
    /**
     * 
     * 获取全部的数据
     * 
     * @param list
     * @return
     */
    public boolean saveDepartmentList(final List<SelectBoxObj> list)
    {
        try
        {
            return redisCache.putMap("COMMON", REDIS_KEY_PREFIX_DEPARTMENT, JSON.toJSONString(list));
        }
        catch (Exception e)
        {
            logger.error("Put DepartmentInfo into redis cause error :" + JSON.toJSONString(list));
            return false;
        }
    }
    
    public List<SelectBoxObj> queryDepartmentList()
    {
        try
        {
            String s = redisCache.getMap("COMMON", REDIS_KEY_PREFIX_DEPARTMENT);
            return JSON.parseArray(s, SelectBoxObj.class);
        }
        catch (Exception e)
        {
            logger.error("Read DepartmentInfo into redis cause error , maybe redis is down !");
            return null;
        }
    }
    
    /**
     * 
     * 根据税源 id 获取部门
     * @param list
     * @param revenueTypeId
     * @return
     */
    public boolean saveDepartmentByRevenueTypeId(final List<SelectBoxObj> list, final String revenueTypeId)
    {
        try
        {
            return redisCache.putMap(REDIS_KEY_PREFIX_DEPT_TAXID, revenueTypeId, JSON.toJSONString(list));
        }
        catch (Exception e)
        {
            logger.error("Put DepartmentInfo into redis cause error :" + JSON.toJSONString(list));
            return false;
        }
    }
    
    public List<SelectBoxObj> getDepartmentByRevenueTypeId(String revenueTypeId)
    {
        try
        {
            String s = redisCache.getMap(REDIS_KEY_PREFIX_DEPT_TAXID, revenueTypeId);
            return JSON.parseArray(s, SelectBoxObj.class);
        }
        catch (Exception e)
        {
            logger.error("Read DepartmentInfo into redis cause error , maybe redis is down !");
            return null;
        }
    }
}
