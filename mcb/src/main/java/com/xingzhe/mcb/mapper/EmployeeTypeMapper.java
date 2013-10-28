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

@CacheNamespace(size = 1024,implementation = LoggingEhcache.class)
public interface EmployeeTypeMapper extends SqlMapper {
	
	
	/**
	 * 查询所有的员工类别信息
	 * @return
	 */
	@Select("select id, name from  employee_type")
	@Results(value={
			@Result(property="id",column="id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
			@Result(property="name",column="name",javaType=String.class,jdbcType=JdbcType.VARCHAR)
			})
	List<SelectBoxObj> getAllEmployeeType();

}
