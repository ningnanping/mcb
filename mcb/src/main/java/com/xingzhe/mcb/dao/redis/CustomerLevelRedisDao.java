package com.xingzhe.mcb.dao.redis;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.xingzhe.framework.cache.redis.RedisCache;
import com.xingzhe.framework.domain.SelectBoxObj;

/**
 * Created with IntelliJ IDEA.
 * User: LuTang
 * Date: 13-10-9
 * Time: 下午7:40
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CustomerLevelRedisDao {

	private static final String PREX_CUSTOMER_LEVEL = "COM.XINGZHE.MCB.DOMAIN.CUSTOMERLEVEL";
	@Autowired
	private RedisCache redisCache;

	/**
	 * 添加到缓存
	 *
	 * @param list
	 */
	public void saveAllCustomerLevel(List<SelectBoxObj> list) {
		redisCache.putMap("COMMON", PREX_CUSTOMER_LEVEL, JSON.toJSONString(list));
	}

	/**
	 * 从缓存中获取
	 *
	 * @return
	 */
	public List<SelectBoxObj> getAllCustomerLevel() {
		return JSON.parseArray((String) redisCache.getMap("COMMON", PREX_CUSTOMER_LEVEL), SelectBoxObj.class);
	}
}
