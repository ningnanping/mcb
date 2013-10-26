package com.xingzhe.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import com.xingzhe.common.domain.User;
import com.xingzhe.framework.mapper.SqlMapper;

import org.mybatis.caches.ehcache.LoggingEhcache;

@CacheNamespace(size = 1024,implementation = LoggingEhcache.class)
public interface UserMapper extends SqlMapper {

	@Results(value = { 
			@Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(column = "user_name", property = "userName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", javaType = String.class, jdbcType = JdbcType.CHAR),
			@Result(column = "is_sys", property = "isSystem", javaType = Integer.class, jdbcType = JdbcType.SMALLINT),
	})
	@Select("SELECT `id`, `user_name`, `password`, `is_sys` FROM `mcb`.`user` WHERE `is_del`= 0 AND  `user_name`=#{userName}")
	public List<User> getUserByName(@Param(value = "userName") String userName);
	
	
	@Update("update `mcb`.`user` set password=#{password} where `user_name`=#{userName}")
	public void  updatePassword(@Param(value = "password") String password,@Param(value = "userName") String userName);
}
