package com.xingzhe.framework.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;

import com.xinzhe.framework.cache.redis.RedisCache;

/**
 * Redis 基类
 * 
 * @author hoopy
 *
 */
public class BaseRedisDao {
	
	@Autowired
	protected RedisCache redisCache;
	
	/**
	 * 部门机构信息缓存KEY
	 */
	public static final String REDIS_KEY_PREFIX_DEPARTMENT = "COM.XINGZHE.ZHZS.DEPARTMENT";
	
	/**
	 * 税源种类缓存key
	 */
	public static final String REDIS_KEY_PREFIX_TAXTYPE = "COM.XINGZHE.ZHZS.TAXTYPE";
	
	/**
	 * 根据税源种类Id 获取表样Key
	 */
	public static final String REDIS_KEY_PREFIX_TABLETYPE_BY_TAXID = "COM.XINGZHE.ZHZS.TABLETYPE.TAXID";
	
	/**
	 * 根据部门的ID 获取文件类型key
	 */
	public static final String REDIS_KEY_PREFIX_FILETYPE_DEPTID = "COM.XINGZHE.ZHZS.FILETYPE.DEPTID";
	
	
	/**
	 * 根据税源种类获取 部门
	 */
	public static final String REDIS_KEY_PREFIX_DEPT_TAXID = "COM.XINGZHE.ZHZS.DEPARTMENT.TAXID";

}
