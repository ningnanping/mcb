package com.xingzhe.mcb.dao.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.xingzhe.framework.cache.redis.RedisCache;
import com.xingzhe.framework.domain.SelectBoxObj;

@Repository
public class EmployeeTypeRedisDao {
	private static final String PREX_REDIS_EMPLOYEE_TYPE = "COM.XINGZHE.MCB.DOMAIN.EMPLOYEETYPE";
	private static final Logger log = LoggerFactory
			.getLogger(EmployeeTypeRedisDao.class);
	@Autowired
	private RedisCache redisCache;

	public List<SelectBoxObj> getAllEmployeeType() {
		try {
			String json = redisCache.getMap("COMMON", PREX_REDIS_EMPLOYEE_TYPE);
			return JSON.parseArray(json, SelectBoxObj.class);
		} catch (Exception e) {
			log.error(e.toString());
		}

		return null;
	}

	public void savetAllEmployeeType(List<SelectBoxObj> list) {
		try {
			redisCache.putMap("COMMON", PREX_REDIS_EMPLOYEE_TYPE,
					JSON.toJSONString(list));
		} catch (Exception e) {
			log.error(e.toString());
		}

	}
}
