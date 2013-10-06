package com.xingzhe.common.redis.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.xingzhe.common.domain.User;
import com.xingzhe.framework.cache.redis.RedisCache;

@Repository
public class UserRedisDao
{
    @Autowired 
    private RedisCache redisCache;
    
    private static final String prex_USER="COM.XINGZHE.COMMON.REDIS.DAO.USERREDISDAO";
    
    public  void  saveUserList(String userName,List<User> list){
        redisCache.putMap(prex_USER,userName,JSON.toJSONString(list));
    }
    
    public  List<User>  getUserList(String userName){
       return  JSON.parseArray((String)redisCache.getMap(prex_USER,userName), User.class);
    }
}
