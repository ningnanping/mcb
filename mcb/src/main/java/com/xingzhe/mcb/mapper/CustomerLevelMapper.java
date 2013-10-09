package com.xingzhe.mcb.mapper;

import com.xingzhe.mcb.domain.CustomerLevel;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: LuTang
 * Date: 13-10-9
 * Time: 下午7:10
 * To change this template use File | Settings | File Templates.
 */
public interface CustomerLevelMapper {

    @Results(value ={
            @Result(column = "id",property = "id",jdbcType = JdbcType.SMALLINT,javaType = Integer.class),
            @Result(column = "name",property = "name",jdbcType = JdbcType.VARCHAR,javaType = String.class)
    })
    @Select("SELECT `id`, `name` FROM `mcb`.`customer_level` t WHERE t.`is_del`=0 ORDER BY id")
    List<CustomerLevel> getAllCustomerLevel();
}
