package com.xingzhe.common.dao.redis;

import com.alibaba.fastjson.JSON;
import com.xingzhe.common.dao.redis.UserLoginCache;
import com.xingzhe.common.domain.User;
import com.xingzhe.framework.cache.redis.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRedisDao {
	private static final Logger log = LoggerFactory.getLogger(UserLoginCache.class);
	private static final String PREX_USER = "COM.XINGZHE.COMMON.REDIS.DAO.USERREDISDAO";
	@Autowired
	private RedisCache redisCache;

	public void saveUserList(String userName, List<User> list) {
		try {
			redisCache.putMap(PREX_USER, userName, JSON.toJSONString(list));
		} catch (Exception e) {
			log.error(e.toString());
		}
	}

	public List<User> getUserList(String userName) {
		try {
			return JSON.parseArray((String) redisCache.getMap(PREX_USER, userName), User.class);
		} catch (Exception e) {
			log.error(e.toString());
		}
		return null;
	}
}
