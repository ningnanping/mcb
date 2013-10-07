package com.xingzhe.mcb.mapper;

import org.apache.ibatis.annotations.Param;

import com.xingzhe.framework.mapper.SqlMapper;

public interface OrderMapper extends SqlMapper
{
    void addOrder(@Param(value = "customerId") int customerId,@Param(value="productList")String productList);
}
