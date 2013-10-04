package com.xingzhe.framework.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.framework.cache.redis.RedisCache;
import com.xingzhe.framework.util.MD5Util;


@Repository
public class UserLoginCache  {

	@Autowired 
	private RedisCache redisCache;
	
	/**
	 * 添加缓存
	 * @param userName
	 * @param planFrom
	 * @param uuid
	 * @return
	 */
	public  String  putAcessToken(String userName,String planFrom,String uuid){
		String acessToken=MD5Util.getInstance().md5s((userName+planFrom+uuid).getBytes());
		redisCache.putMap(userName+planFrom,uuid,acessToken);
		return acessToken;
	}
	
	/**
	 * 获取缓存
	 * @param userName
	 * @param planFrom
	 * @param uuid
	 * @return
	 */
	public  String  getAcessToken(String userName,String planFrom,String uuid){
		return redisCache.getMap(userName+planFrom,uuid);
	}
	
	public void delAcessToken(String userName,String planFrom){
		redisCache.remove(userName+planFrom);
	}
	
}
