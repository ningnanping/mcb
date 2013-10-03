package com.xingzhe.framework.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.framework.cache.redis.RedisCache;
import com.xingzhe.framework.util.MD5Util;


@Repository
public class UserLoginCache  {

	@Autowired 
	private RedisCache redisCache;
	
	public  String  putAcessToken(String userName,String planFrom,String uuid){
		String acessToken=MD5Util.getInstance().md5s((userName+planFrom+uuid).getBytes());
		redisCache.putMap(userName, planFrom+uuid,acessToken);
		return acessToken;
	}
	
	public  String  getAcessToken(String userName,String planFrom,String uuid){
		return redisCache.getMap(userName, planFrom+uuid);
	}
	
}
