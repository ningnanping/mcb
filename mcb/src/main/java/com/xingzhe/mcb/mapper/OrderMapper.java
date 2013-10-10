package com.xingzhe.mcb.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import com.xingzhe.framework.mapper.SqlMapper;
import org.mybatis.caches.ehcache.LoggingEhcache;

@CacheNamespace(size = 1024,implementation = LoggingEhcache.class)
public interface OrderMapper extends SqlMapper
{
    void addOrder(@Param(value = "customerId") int customerId,@Param(value="productList")String productList);
}
