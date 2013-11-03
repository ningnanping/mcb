package com.xingzhe.mcb.dao.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.xingzhe.framework.cache.redis.RedisCache;
import com.xingzhe.framework.domain.SelectBoxObj;


@Repository
public class EmployeeRedisDao {
	private static final String PREX_EMPLOYEE = "COM.XINGZHE.MCB.DOMAIN.EMPLOYEE";
	@Autowired
	private RedisCache redisCache;
	
	
	/**
	 * 添加到缓存
	 *
	 * @param list
	 */
	public void saveAllEmployee(List<SelectBoxObj> list) {
		redisCache.putMap("COMMON", PREX_EMPLOYEE, JSON.toJSONString(list));
	}

	/**
	 * 从缓存中获取
	 *
	 * @return
	 */
	public List<SelectBoxObj> getAllEmployee() {
		return JSON.parseArray((String) redisCache.getMap("COMMON", PREX_EMPLOYEE), SelectBoxObj.class);
	}
}
