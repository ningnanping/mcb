package com.xinzhe.system.execle.dao.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.xingzhe.system.execle.domain.Excel;
import com.xinzhe.framework.cache.redis.RedisCache;

@Repository
public class ExcelRedisDao {
	
	@Autowired
	private RedisCache redisCache;
	
	
	private  static final String  WJDR_EXCEL_REDIS_PREDIFX="COM.XINGZHE.SYSTEM.EXECLE.DAO.REDIS";
	
	
	public  void saveExcel(String id,List<Excel> list){
		redisCache.put(WJDR_EXCEL_REDIS_PREDIFX+id, JSON.toJSONString(list));
	} 
 
	
	public List<Excel>  getExcel(String id) {
		return JSON.parseArray((String)redisCache.get(WJDR_EXCEL_REDIS_PREDIFX+id), Excel.class);
	}
}
