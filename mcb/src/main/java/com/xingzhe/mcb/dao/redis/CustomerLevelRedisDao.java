package com.xingzhe.mcb.dao.redis;


import com.alibaba.fastjson.JSON;
import com.xingzhe.framework.cache.redis.RedisCache;
import com.xingzhe.mcb.domain.CustomerLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public void saveAllCustomerLevel(List<CustomerLevel> list) {
        redisCache.putMap("COMMON", PREX_CUSTOMER_LEVEL, JSON.toJSONString(list));
    }

    public List<CustomerLevel> getAllCustomerLevel() {
        return JSON.parseArray((String) redisCache.getMap("COMMON", PREX_CUSTOMER_LEVEL), CustomerLevel.class);
    }
}
