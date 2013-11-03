package com.xingzhe.mcb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.caches.ehcache.LoggingEhcache;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.framework.mapper.SqlMapper;

/**
 * Created with IntelliJ IDEA.
 * User: LuTang
 * Date: 13-10-9
 * Time: 下午7:10
 * To change this template use File | Settings | File Templates.
 */
@CacheNamespace(size = 1024,implementation = LoggingEhcache.class)
public interface CustomerLevelMapper extends SqlMapper{

    @Results(value ={
            @Result(column = "id",property = "id",jdbcType = JdbcType.SMALLINT,javaType = Integer.class),
            @Result(column = "name",property = "name",jdbcType = JdbcType.VARCHAR,javaType = String.class)
    })
    @Select("SELECT `id`, `name` FROM `mcb`.`customer_level` t WHERE t.`is_del`=0 ORDER BY id")
    List<SelectBoxObj> getAllCustomerLevel();
}
